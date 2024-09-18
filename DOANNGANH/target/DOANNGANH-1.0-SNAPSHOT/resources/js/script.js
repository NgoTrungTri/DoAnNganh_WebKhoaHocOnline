document.addEventListener('DOMContentLoaded', function() {
    const form = document.querySelector('form');

    form.addEventListener('submit', function(event) {
        let valid = true;
        const username = document.getElementById('username').value;
        const password = document.getElementById('password').value;
        const email = document.getElementById('email').value;
        const birth = document.getElementById('birth').value;

        // Clear previous error messages
        document.querySelectorAll('.form-control').forEach(input => input.classList.remove('is-invalid'));
        document.querySelectorAll('.invalid-feedback').forEach(feedback => feedback.remove());

        // Validate username
        if (username.trim() === '') {
            showError('username', 'Tên đăng nhập không được để trống');
            valid = false;
        }

        // Validate password
        if (password.trim() === '') {
            showError('password', 'Mật khẩu không được để trống');
            valid = false;
        }

        // Validate email
        if (!validateEmail(email)) {
            showError('email', 'Email không hợp lệ');
            valid = false;
        }

        // Validate birthdate
        if (birth.trim() === '') {
            showError('birth', 'Ngày sinh không được để trống');
            valid = false;
        }

        if (!valid) {
            event.preventDefault(); // Prevent form submission if invalid
        }
    });

    function showError(id, message) {
        const input = document.getElementById(id);
        input.classList.add('is-invalid');
        const feedback = document.createElement('div');
        feedback.className = 'invalid-feedback';
        feedback.innerText = message;
        input.parentElement.appendChild(feedback);
    }

    function validateEmail(email) {
        const re = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        return re.test(String(email).toLowerCase());
    }
});