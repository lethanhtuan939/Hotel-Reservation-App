const url = 'http://localhost:8080/api/v1/auth';
function start() {
    const email = getCookie("email");
    document.getElementById('email').value = email;
    const btnLogin = document.getElementById('btnLogin');
    btnLogin.addEventListener('click', login);
}

function login() {
    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;

    if (email === '' || password === '') return;

    const userLogin = {
        email: email,
        password: password
    }

    checkLogin(userLogin);
}

function checkLogin(userLogin) {
    const options = {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(userLogin)
    }

    fetch(url + '/login', options)
        .then((response) => response.json())
        .then((res) => {
            if (+res.code != 200) {
                document.querySelector('.alert').classList.remove('hide');
                swal({
                    title: res.message,
                    icon: "error",
                    button: "OK",
                });
            } else {
                document.querySelector('.alert').classList.add('hide')
                localStorage.setItem("access_token", res.accessToken);
                localStorage.setItem("email", userLogin.email);

                const isRemberme = document.getElementById('rememberMe');
                if (isRemberme.checked) {
                    setCookie("email", userLogin.email, 7);
                } else {
                    eraseCookie("email");
                }

                window.location.href = 'index.html';
            }

        })
}

function setCookie(name, value, days) {
    var expires = "";
    if (days) {
        var date = new Date();
        date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
        expires = "; expires=" + date.toUTCString();
    }
    document.cookie = name + "=" + (value || "") + expires + "; path=/";
}

function getCookie(name) {
    var nameEQ = name + "=";
    var ca = document.cookie.split(';');
    for (var i = 0; i < ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') c = c.substring(1, c.length);
        if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length, c.length);
    }
    return null;
}

function eraseCookie(name) {
    document.cookie = name + '=; Path=/; Expires=Thu, 01 Jan 1970 00:00:01 GMT;';
}

start();