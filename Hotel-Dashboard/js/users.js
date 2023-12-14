const api = 'http://localhost:8080/api/v1/users';
const apiFile = 'http://localhost:8080/api/v1/file/';
const apiUser = 'http://localhost:8080/api/v1/users';
const apiLocation = 'http://localhost:8080/api/v1/locations';

const editModal = new bootstrap.Modal(document.getElementById('editModal'));

start();

function start() {
    fetchAllUsers();
    handleSaveChange();
}

function fetchAllUsers() {
    fetch(api)
        .then((response) => response.json())
        .then((users) => {
            renderUsers(users.data)
        })
}

function renderUsers(users) {
    users.forEach(user => {
        displayUser(user);
    });
}

function displayUser(user) {
    const userTable = document.getElementById('userTable');

    const row = document.createElement("tr");
    const idRow = 'row-' + user.id;
    row.classList.add(idRow);

    const idCell = document.createElement("td");
    idCell.classList.add('id-' + user.id);
    idCell.classList.add("align-middle");
    idCell.textContent = user.id;
    row.appendChild(idCell);

    const imageCell = document.createElement("td");
    imageCell.classList.add("align-middle");
    imageCell.classList.add('image-' + user.id);

    const objectURL = apiFile + user.image;
    const imgElement = document.createElement("img");
    imgElement.classList.add('img-' + user.id);
    imgElement.setAttribute("src", objectURL);
    imgElement.setAttribute("width", "60");
    imgElement.setAttribute("height", "60");
    imageCell.appendChild(imgElement);
    row.appendChild(imageCell);

    const nameCell = document.createElement("td");
    nameCell.classList.add('name-' + user.id);
    nameCell.classList.add("align-middle");
    nameCell.textContent = user.name;
    row.appendChild(nameCell);

    const genderCell = document.createElement("td");
    genderCell.classList.add('gender-' + user.id);
    genderCell.classList.add("align-middle");
    genderCell.style.fontSize = "24px";
    genderCell.innerHTML = `<i class="fa-solid fa-user ${user.gender === 'male' ? 'text-primary' :
        user.gender === 'female' ? 'pink' : 'purple'
        }"></i>`;
    row.appendChild(genderCell);

    const emailCell = document.createElement("td");
    emailCell.classList.add('email-' + user.id);
    emailCell.classList.add("align-middle");
    emailCell.textContent = user.email;
    row.appendChild(emailCell);

    const phoneNumber = document.createElement("td");
    phoneNumber.classList.add('phone-' + user.id);
    phoneNumber.textContent = user.phoneNumber;
    row.appendChild(phoneNumber);

    const locationCell = document.createElement("td");
    locationCell.classList.add('location-' + user.id);
    locationCell.classList.add("align-middle");
    locationCell.textContent = user.location?.name;
    row.appendChild(locationCell);

    const enabled = document.createElement("td");
    enabled.classList.add('enabled-' + user.id);
    enabled.classList.add("align-middle");
    enabled.innerHTML = user.enabled
        ? '<span class="badge bg-success">Enabled</span>'
        : '<span class="badge bg-danger">Disabled</span>';
    row.appendChild(enabled);

    const role = document.createElement("td");
    role.classList.add('role-' + user.id);
    role.classList.add("align-middle");
    role.innerHTML = `<span class="badge ${user.roles[0].name === 'ADMIN' ? 'bg-danger' : 'bg-primary'
        }">${user.roles[0].name}</span>`;

    row.appendChild(role);

    fetch(apiUser + '/?e=' + localStorage.getItem('email'))
        .then(response => response.json())
        .then(userLogin => {
            if (+userLogin.data.roles[0].id === 1) {
                const actionCell = document.createElement("td");
                actionCell.classList.add('action-' + user.id);
                actionCell.classList.add("align-middle");

                const editButton = document.createElement("button");
                editButton.classList.add("btn", "btn-primary", "mx-2", "btnEdit-" + user.id);
                editButton.innerHTML = '<i class="fa-solid fa-pen-to-square"></i>&nbsp Edit';

                editButton.addEventListener('click', async () => {
                    console.log(user)

                    await pinDataToSelect();
                    pinUserToModal(user);
                    editModal.show();
                });

                actionCell.appendChild(editButton);
                row.appendChild(actionCell);
            }
        })

    userTable.appendChild(row);
}

function pinUserToModal(user) {
    const id = document.getElementById('editId');
    id.value = user.id;

    const objectURL = apiFile + user.image;
    const imgElement = document.getElementById('img')
    imgElement.setAttribute("src", objectURL);

    const email = document.getElementById('editEmail');
    email.value = user.email;

    const name = document.getElementById('editName');
    name.value = user.name;

    const phoneNumber = document.getElementById('editPhone');
    phoneNumber.value = user.phoneNumber;

    const gender = document.getElementById('editGender');
    for (const option of gender.options) {
        if (option.value === user.gender) {
            option.selected = true;
            break;
        }
    }

    const location = document.getElementById('editLocation');
    for (const option of location.options) {
        if (+option.value === user.location?.id) {
            option.selected = true;
            break;
        }
    }

    const state = document.getElementById('editState');
    for (const option of state.options) {
        let value = stringToBool(option.value)
        if (value === user.enabled) {
            option.selected = true;
            break;
        }
    }

    const role = document.getElementById('editRole');
    for (const option of role.options) {
        if (+option.value === user.roles[0].id) {
            option.selected = true;
            break;
        }
    }
}

function stringToBool(val) {
    return (val + '').toLowerCase() === 'true';
}

async function pinDataToSelect() {
    const [rolesResponse, locationResponse] = await Promise.all([
        fetch(api + '/roles').then((response) => response.json()),
        fetch(apiLocation).then((response) => response.json())
    ]);

    const editRoleSelect = document.getElementById('editRole');
    handlePinData(rolesResponse.data, editRoleSelect);

    const editLocationSelect = document.getElementById('editLocation');
    handlePinData(locationResponse.data, editLocationSelect);
}

function handlePinData(data, elementSelect) {
    elementSelect.innerHTML = '';
    data.forEach((item) => {
        const option = document.createElement('option');
        option.value = item.id;
        option.textContent = item.name;
        elementSelect.appendChild(option);
    });
}

// -------------------------------------------------------
function handleSaveChange() {
    const btnSaveChange = document.getElementById('btnSaveChange');
    btnSaveChange.addEventListener('click', () => {
        const id = document.getElementById('editId').value;
        const email = document.getElementById('editEmail').value;
        const name = document.getElementById('editName').value;
        const phoneNumber = document.getElementById('editPhone').value;
        const location = document.getElementById('editLocation');
        const gender = document.getElementById('editGender').value;
        const state = document.getElementById('editState').value;
        const role = document.getElementById('editRole');

        const user = {
            id: id,
            email: email,
            name: name,
            phoneNumber: phoneNumber,
            gender: gender,
            enabled: state,
            location: {
                id: location.value,
                name: location.options[location.selectedIndex].text
            },
            roles: [
                {
                    id: role.value,
                    name: role.options[role.selectedIndex].text
                }
            ]
        }

        const userData = JSON.stringify(user);

        const formData = new FormData();
        formData.append('data', userData);

        const imageInput = document.getElementById('image');
        if (imageInput.files.length > 0) {
            formData.append('file', imageInput.files[0]);
        }

        const token = localStorage.getItem('access_token');

        swal({
            title: "Are you sure to update this?",
            icon: "warning",
            buttons: true,
            dangerMode: true,
        }).then((value) => {
            if (value) {
                const options = {
                    method: "PUT",
                    headers: {
                        'Authorization': `Bearer ${token}`,
                    },
                    body: formData
                }

                fetch(api + '/' + id, options)
                    .then(response => response.json())
                    .then(userResponse => {
                        updateUserTable(userResponse.data);
                        editModal.hide();
                    })
                    .catch(error => {
                        console.error('Error: ', error);

                    });
                swal("Update successfully!", {
                    icon: "success",
                });
            }
        })
    })
}

function updateUserTable(user) {
    const rowCurrent = document.querySelector('.row-' + user.id);
    const tdChilds = rowCurrent.querySelectorAll('td');
    tdChilds.forEach(item => {
        rowCurrent.removeChild(item);
    });

    const idCell = document.createElement("td");
    idCell.classList.add("align-middle");
    idCell.textContent = user.id;

    const imageCell = document.createElement("td");
    imageCell.classList.add("align-middle");
    imageCell.classList.add('image-' + user.id);

    const objectURL = apiFile + user.image;
    const imgElement = document.createElement("img");
    imgElement.classList.add('img-' + user.id);
    imgElement.setAttribute("src", objectURL);
    imgElement.setAttribute("width", "60");
    imgElement.setAttribute("height", "60");
    imageCell.appendChild(imgElement);

    const nameCell = document.createElement("td");
    nameCell.classList.add("align-middle");
    nameCell.textContent = user.name;

    const genderCell = document.createElement("td");
    genderCell.classList.add("align-middle");
    genderCell.style.fontSize = "24px";
    genderCell.innerHTML = `<i class="fa-solid fa-user ${user.gender === 'male' ? 'text-primary' :
        user.gender === 'female' ? 'pink' : 'purple'
        }"></i>`;

    const emailCell = document.createElement("td");
    emailCell.classList.add("align-middle");
    emailCell.textContent = user.email;

    const phoneNumber = document.createElement("td");
    phoneNumber.textContent = user.phoneNumber;

    const locationCell = document.createElement("td");
    locationCell.classList.add("align-middle");
    locationCell.textContent = user.location.name;

    const enabled = document.createElement("td");
    enabled.classList.add("align-middle");
    enabled.innerHTML = user.enabled
        ? '<span class="badge bg-success">Enabled</span>'
        : '<span class="badge bg-danger">Disabled</span>';

    const role = document.createElement("td");
    role.classList.add("align-middle");
    role.innerHTML = `<span class="badge ${user.roles[0].name === 'ADMIN' ? 'bg-success' : 'bg-primary'
        }">${user.roles[0].name}</span>`;

    const actionCell = document.createElement("td");
    actionCell.classList.add("align-middle");

    const editButton = document.createElement("button");
    editButton.classList.add("btn", "btn-primary", "mx-2");
    editButton.innerHTML = '<i class="fa-solid fa-pen-to-square"></i>&nbsp Edit';

    editButton.addEventListener('click', async () => {
        await pinDataToSelect();
        pinUserToModal(user);
        editModal.show();
    });

    actionCell.appendChild(editButton);

    rowCurrent.appendChild(idCell);
    rowCurrent.appendChild(imageCell);
    rowCurrent.appendChild(nameCell);
    rowCurrent.appendChild(genderCell);
    rowCurrent.appendChild(emailCell);
    rowCurrent.appendChild(phoneNumber);
    rowCurrent.appendChild(locationCell);
    rowCurrent.appendChild(enabled);
    rowCurrent.appendChild(role);
    rowCurrent.appendChild(actionCell);
}


