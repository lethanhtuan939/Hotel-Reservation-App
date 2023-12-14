const url = 'http://localhost:8080/api/v1/auth/';

const emailElement = document.querySelector('.user-name');
const userEmail = localStorage.getItem('email');
const userImage = document.querySelector('.user-image');

fetch('http://localhost:8080/api/v1/users/?e=' + userEmail)
    .then((response) => response.json())
    .then((user) => {
        if (user.data.name) {
            emailElement.innerText = user.data.name;
        } else {
            emailElement.innerText = 'Admin';
        }

        const objectURL = 'http://localhost:8080/api/v1/file/' + user.data.image;
        userImage.setAttribute("src", objectURL);
    })

// ----------------------------------------------------

const logoutBtn = document.querySelector('#logoutBtn a');
logoutBtn.addEventListener('click', function (event) {
    event.preventDefault();

    const options = {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        }
    }

    fetch(url + 'logout', options)
        .then((response) => response.json())
        .then((res) => {

        })

    localStorage.clear();
    window.location.href = "login.html";
});

// ----------------------------------------------------
