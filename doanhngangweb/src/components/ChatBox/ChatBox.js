import { useEffect, useState } from "react";
import { addDoc, collection, onSnapshot, serverTimestamp, where, query, getDocs, setDoc, doc } from "firebase/firestore";
import { db } from "../../configs/Firebase";
import { authApi, endpoints } from "../../configs/APIs";

const ChatBox = ({ user }) => {
    const [messages, setMessages] = useState([]);
    const [text, setText] = useState("");// Tin nhắn
    const [recipient, setRecipient] = useState({});/// Người nhận tin nhắn
    const [users, setUsers] = useState([]);
    const [userDetails, setUserDetails] = useState({});
    const [conversationId, setConversationId] = useState(null);
    const [searchText, setSearchText] = useState("");/// Tìm kiếm người dùng

    useEffect(() => {
        if (!user) return;
        /// Fetch những cuộc trò chuyện của user đăng nhập
        const fetchConversations = async () => {
            try {
                const conversationsRef = collection(db, "conversations");
                const q = query(conversationsRef, where("users", "array-contains", user.username));
                const querySnapshot = await getDocs(q);
                ///Tạo Set để lưu trữ danh sách các user chat với người dùng hiện tại
                let userList = new Set();
                querySnapshot.forEach((doc) => {
                    const data = doc.data();
                    data.users.forEach((username) => {
                        if (username !== user.username) {
                            userList.add(username);
                        }
                    });
                });

                const usersArray = Array.from(userList);
                setUsers(usersArray);

                // Fetch thông tin các user chat lưu vào UserDetails với người dùng hiện tại để hiện avatar...
                const authAPI = authApi();
                const userDetailsResponse = await Promise.all(
                    usersArray.map(username => authAPI.get(`${endpoints.userbyusername}?username=${username}`))/// sử dụng arrow function cho nhanh
                );
                /// Biến đổi respone của APi thành dối tượng
                const userDetailsObject = userDetailsResponse.reduce((acc, curr) => { 
                    acc[curr.data.username] = curr.data;
                    return acc;
                }, {});
                setUserDetails(userDetailsObject);
                // console.log(userDetailsObject);
            } catch (error) {
                console.error("Không thể lấy người dùng chat với bạn ", error);
            }
        };

        fetchConversations();
    }, [user]);


    ///Lấy ConversationsId
    useEffect(() => {
        if (!user || !recipient.username) return;

        const fetchConversation = async () => {
            try {
                const conversationsRef = collection(db, "conversations");
                const q = query(conversationsRef, where("users", "array-contains", user.username));
                const querySnapshot = await getDocs(q);
                ///Tạo biến 
                let foundConversation = null;

                ////Duyệt tìm cuộc trò truyện nào có thông tin của người nhận và lưu id cuộc trò chuyện
                querySnapshot.forEach((doc) => {
                    const data = doc.data();
                    if (data.users.includes(recipient.username)) {
                        foundConversation = { id: doc.id, ...data };
                    }
                });

                ///Nếu chưa có thì tạo  
                if (!foundConversation) {
                    const newConversationRef = doc(conversationsRef);
                    await setDoc(newConversationRef, { users: [user.username, recipient.username] });
                    setConversationId(newConversationRef.id);
                } else {
                    setConversationId(foundConversation.id);
                }
            } catch (error) {
                console.error("Error fetching conversation: ", error);
            }
        };

        fetchConversation();
    }, [user, recipient]);

    ///Fetch dữ liệu của cuộc trò chuyện -- dựa trên id của cuộc trò chuyện bởi hàm fetchConversation(); và ConversationId
    useEffect(() => {
        if (!conversationId) return;

        const messagesRef = collection(db, "messages");
        const q = query(messagesRef, where("conversationId", "==", conversationId));
        const unsubscribe = onSnapshot(q, (snapshot) => {
            const data = [];
            snapshot.forEach((doc) => {
                data.push({ id: doc.id, ...doc.data() });
            });
            const sortedData = data.sort((a, b) => a.timestamp - b.timestamp);
            setMessages(sortedData);
            // setMessages(data);
        });

        return () => unsubscribe(); /// húy listener
    }, [conversationId]);

    ///Hàm gửi tin nhắn
    const sendMessage = async () => {
        if (!user || !conversationId) {
            console.error("Lỗi không có người dùng hoặc cuộc trò chuyện nào!!");
            return;
        }
        try {
            await addDoc(collection(db, "messages"), {
                username: user.username,
                mess: text,
                conversationId,
                timestamp: serverTimestamp(),
            });
            setText("");
        } catch (error) {
            console.error("Lỗi gửi tin nhắn: ", error);
        }
    };

    const formatTime = (timestamp) => {
        if (timestamp) {
            const date = timestamp.toDate();
            const hours = date.getHours().toString().padStart(2, '0');
            const minutes = date.getMinutes().toString().padStart(2, '0');
            return `${hours}:${minutes}`;
        }
        return "";
    };

    const handleKeyPress = (e) => {
        if (e.key === 'Enter') {
            sendMessage();
        }
    };

    const handleRecipientClick = async (username) => {
        try {
            const authAPI = authApi();
            const response = await authAPI.get(`${endpoints.userbyusername}?username=${username}`);
            setRecipient(response.data);
            console.log(response.data);
        } catch (error) {
            console.error("Error fetching recipient: ", error);
        }
    };

    // Hàm xử lý tìm kiếm
    const handleSearch = async () => {
        try {
            const authAPI = authApi();
            const response = await authAPI.get(`${endpoints.userbyusername}?username=${searchText}`);
            if (response.data) {
                setRecipient(response.data);

                // Thêm người dùng vào danh sách nếu chưa có
                if (!users.includes(response.data.username)) {
                    setUsers([...users, response.data.username]);
                }

                setUserDetails(prevDetails => ({
                    ...prevDetails,
                    [response.data.username]: response.data
                }));
                
                setSearchText("");
            } else {
                alert("Không tồn tại người dùng này!");
            }
        } catch (error) {
            console.error("Lỗi tìm kiếm: ", error);
            alert("Không tồn tại người dùng này");
        }
    };

    const handleSearchKeyPress = (e) => {
        if (e.key === 'Enter') {
            handleSearch();
        }
    };

    if (!user) {
        return <div>Please log in to join the chat</div>;
    }

    return (
        <section style={{ backgroundColor: '#f0f0f0' }}>
            <div className="container py-5">
                <div className="row">
                    <div className="col-md-12">
                        <div className="card" id="chat3" style={{ borderRadius: '15px' }}>
                            <div className="card-body">
                                <div className="row">
                                    <div className="col-md-6 col-lg-5 col-xl-4 mb-4 mb-md-0" style={{ borderRight: '1px solid #ccc' }}>
                                        {/* Vùng hiện các phòng chat */}
                                        <div className="p-3">
                                            <div className="input-group rounded mb-3">
                                                <input
                                                    type="search"
                                                    className="form-control rounded"
                                                    placeholder="Search"
                                                    aria-label="Search"
                                                    aria-describedby="search-addon"
                                                    value={searchText}
                                                    onChange={(e) => setSearchText(e.target.value)}
                                                    onKeyDown={handleSearchKeyPress}
                                                />
                                                <span className="input-group-text border-0" id="search-addon" onClick={handleSearch}>
                                                    <i className="fas fa-search">
                                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
                                                        <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001q.044.06.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1 1 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0"/>
                                                    </svg>
                                                    </i>
                                                </span>
                                            </div>
                                            <div style={{ position: 'relative', height: '400px', overflowY: 'auto' }}>
                                                <ul className="list-unstyled mb-0">
                                                    {users.map((username) => (
                                                        <li key={username} className="p-2 border-bottom">
                                                            <a href="#!" className="d-flex justify-content-between" onClick={() => handleRecipientClick(username)}>
                                                                <div className="d-flex flex-row">
                                                                    <div>
                                                                        <img
                                                                            src={userDetails[username]?.avatar || '/default-avatar.png'}
                                                                            alt="avatar"
                                                                            className="d-flex align-self-center me-3"
                                                                            width="60"
                                                                            height="60"
                                                                        />
                                                                        <span className="badge bg-success badge-dot"></span>
                                                                    </div>
                                                                    <div className="pt-1">
                                                                        <p className="fw-bold mb-0">{username}</p>
                                                                        <p className="small text-muted">Hello, Are you there?</p>
                                                                    </div>
                                                                </div>
                                                                <div className="pt-1">
                                                                    <p className="small text-muted mb-1">Just now</p>
                                                                </div>
                                                            </a>
                                                        </li>
                                                    ))}
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                    {/* Phần hiện tin nhắn */}
                                    <div className="col-md-6 col-lg-7 col-xl-8">
                                        <div className="pt-3 pe-3" style={{ position: 'relative', height: '400px', overflowY: 'auto' }}>
                                            <ul style={{ listStyleType: 'none', padding: 0 }}>
                                                {messages.map((message) => (
                                                    <li key={message.id} className={`d-flex ${message.username === user.username ? 'justify-content-end' : 'justify-content-start'} mb-2`}>
                                                        <div className="d-flex flex-row align-items-center">
                                                            {message.username !== user.username && (
                                                                <img src={userDetails[message.username]?.avatar || '/default-avatar.png'} alt="avatar" style={{ width: '45px', height: '45px', marginRight: '10px' }} />
                                                            )}
                                                            <div>
                                                                <p className={`small p-2 mb-1 mt-2 rounded-3 ${message.username === user.username ? 'bg-primary text-white' : 'bg-body-tertiary'}`}>
                                                                    {message.mess}
                                                                </p>
                                                                <p className="small text-muted mb-0">{formatTime(message.timestamp)}</p>
                                                            </div>
                                                            {/* {message.username === user.username && (
                                                                <img src={user.avatar || '/default-avatar.png'} alt="avatar" style={{ width: '45px', height: '45px', marginLeft: '10px' }} />
                                                            )} */}
                                                        </div>
                                                    </li>
                                                ))}
                                            </ul>
                                        </div>
                                        {/* phần gửi tin nhắn */}
                                        <div className="text-muted d-flex justify-content-start align-items-center pe-3 pt-3 mt-2">
                                            <input
                                                type="text"
                                                className="form-control form-control-lg"
                                                placeholder="Type message"
                                                value={text}
                                                onKeyDown={handleKeyPress}
                                                onChange={(e) => setText(e.target.value)}
                                            />
                                            <a className="ms-1 text-muted" href="#!"><i className="fas fa-paperclip"></i></a>
                                            <a className="ms-3 text-muted" href="#!"><i className="fas fa-smile"></i></a>
                                            <a className="ms-3" href="#!" onClick={sendMessage}><i className="fas fa-paper-plane"></i>SEND</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    );
};

export default ChatBox;

