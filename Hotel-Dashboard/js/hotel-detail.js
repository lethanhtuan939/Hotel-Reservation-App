const apiHotel = 'http://localhost:8080/api/v1/hotels';
const apiFile = 'http://localhost:8080/api/v1/file/';
const apiLocation = 'http://localhost:8080/api/v1/locations';
const apiRoom = 'http://localhost:8080/api/v1/rooms';

const editModal = new bootstrap.Modal(document.getElementById('roomEditModal'));
const addModal = new bootstrap.Modal(document.getElementById('roomAddModal'));
const paginationContainer = document.querySelector(".pagination");

var hotelId;
var totalPages;
var currentPage;
var pageSize;

start();
function start() {
    const location = document.getElementById('newLocation');
    const urlParams = new URLSearchParams(window.location.search);
    hotelId = urlParams.get('id');
    currentPage = 1;
    pageSize = 5;

    pinDataToSelect(location, apiLocation)
    bindDingHotel(hotelId);
    handleSaveChangeHotel();
    handleBtnAddRoom();
    handleCreateRoom();

    fetchAllRoomOfHotel(hotelId, currentPage, pageSize);
    handleUpdate();
}

function bindDingHotel(hotelId) {
    fetch(apiHotel + '/' + hotelId)
        .then((response) => response.json())
        .then((hotel) => {
            handleBinding(hotel.data);
        })
}

function handleBinding(hotel) {
    document.getElementById('newId').value = hotel.id;
    document.getElementById('newName').value = hotel.name;
    document.getElementById('newAcreage').value = hotel.acreage;

    const location = document.getElementById('newLocation');
    for (const option of location.options) {
        if (+option.value === hotel.location.id) {
            option.selected = true;
            break;
        }
    }
    const state = document.getElementById('newState');
    for (const option of state.options) {
        let value = stringToBool(option.value)
        if (value === hotel.active) {
            option.selected = true;
            break;
        }
    }
    document.getElementById('newRate').value = hotel.rating;
    const image = document.getElementById('img');
    const objectURL = apiFile + hotel.image;
    image.setAttribute('src', objectURL);
}

function stringToBool(val) {
    return (val + '').toLowerCase() === 'true';
}

async function pinDataToSelect(select, api) {
    await fetch(api)
        .then((response) => response.json())
        .then(res => {
            handlePinData(res.data, select);
        })
}

function handlePinData(data, elementSelect) {
    elementSelect.innerHTML = '<option value="" selected="selected">--Select--</option>';
    data.forEach((item) => {
        const option = document.createElement('option');
        option.value = item.id;
        option.textContent = item.name;
        elementSelect.appendChild(option);
    });
}

function getHotel() {
    const id = document.getElementById('newId').value;
    const name = document.getElementById('newName').value;
    const acreage = document.getElementById('newAcreage').value;
    const location = document.getElementById('newLocation');
    const state = document.getElementById('newState').value;
    const rating = document.getElementById('newRate').value;

    const hotel = {
        id: id,
        name: name,
        acreage: acreage,
        location: {
            id: location.value,
            name: location.options[location.selectedIndex].text
        },
        active: state,
        rating: rating
    }

    return hotel;
}

function handleSaveChangeHotel() {
    const btnSaveChange = document.getElementById('btnSave');

    btnSaveChange.addEventListener('click', () => {
        swal({
            title: "Are you sure to update this?",
            icon: "warning",
            buttons: true,
            dangerMode: true,
        }).then((value) => {
            if (value) {
                const hotel = getHotel();
                const image = document.getElementById('newImage');

                const data = JSON.stringify(hotel);

                const formData = new FormData();
                formData.append('data', data);

                if (image.files.length > 0) {
                    formData.append('file', image.files[0]);
                }
                const token = localStorage.getItem('access_token');

                const options = {
                    method: "PUT",
                    headers: {
                        'Authorization': `Bearer ${token}`,
                    },
                    body: formData
                }

                fetch(apiHotel + '/' + hotel.id, options)
                    .then(response => response.json())
                    .then(hotelResponse => {
                        handleBinding(hotelResponse.data);
                        swal("Update successfully!", {
                            icon: "success",
                        });
                    })
                    .catch(error => {
                        console.error('Error: ', error);
                        swal("Error!", "An error occurs in server!", "error");
                    });
            }
        });
    });

}

function fetchAllRoomOfHotel(hotelId, currentPage, pageSize) {
    fetch(apiHotel + '/' + hotelId + '/room?p=' + currentPage + '&s=' + pageSize)
        .then(response => response.json())
        .then(rooms => {
            renderRoom(rooms.data);
            currentPage = rooms.currentPage;
            changePageSize(currentPage);
            generateRoomPagination(rooms.totalPages, currentPage, pageSize);
        })
}

function renderRoom(rooms) {
    if (rooms === undefined || rooms.length === 0) {
        document.getElementById('notice').textContent = 'This hotel has no rooms!';
    } else {
        document.getElementById('notice').textContent = '';
        rooms.forEach(room => {
            displayRoom(room);
        })
    }
}

function displayRoom(room) {
    const roomTable = document.getElementById('roomTable');
    const row = document.createElement("tr");
    const idRow = 'row-' + room.id;
    row.classList.add(idRow);

    createRoomRow(room, row);

    roomTable.appendChild(row);
}

function createRoomRow(room, row) {
    const idCell = document.createElement("td");
    idCell.classList.add('id-' + room.id);
    idCell.classList.add("align-middle");
    idCell.textContent = room.id;
    row.appendChild(idCell);

    const imageCell = document.createElement("td");
    imageCell.classList.add("align-middle");
    imageCell.classList.add('image-' + room.id);
    row.appendChild(imageCell);

    const objectURL = apiFile + room.image;
    const imgElement = document.createElement("img");
    imgElement.classList.add('img-' + room.id);
    imgElement.setAttribute("src", objectURL);
    imgElement.setAttribute("width", "80");
    imgElement.setAttribute("height", "80");
    imageCell.appendChild(imgElement);

    const floorCell = document.createElement("td");
    floorCell.classList.add('id-' + room.id);
    floorCell.classList.add("align-middle");
    floorCell.textContent = room.floor;
    row.appendChild(floorCell);

    const priceCell = document.createElement("td");
    priceCell.classList.add('id-' + room.id);
    priceCell.classList.add("align-middle");
    priceCell.textContent = room.price + '$';
    row.appendChild(priceCell);

    const stateCell = document.createElement("td");
    stateCell.classList.add('state-' + room.id);
    stateCell.classList.add("align-middle");
    stateCell.innerHTML = room.state === 'PHÒNG TRỐNG'
        ? `<span class="badge bg-success">${room.state}</span>`
        : `<span class="badge bg-warning">${room.state}</span>`

    row.appendChild(stateCell);

    const saleCell = document.createElement("td");
    saleCell.classList.add('id-' + room.id);
    saleCell.classList.add("align-middle");
    saleCell.textContent = room.sale + '%';
    row.appendChild(saleCell);

    const typeCell = document.createElement("td");
    typeCell.classList.add('id-' + room.id);
    typeCell.classList.add("align-middle");
    typeCell.textContent = room.roomType.name;
    let bg = '';
    switch (room.roomType.id) {
        case 7:
            bg = `<span class="badge bg-success">${room.roomType.name}</span>`;
            break;
        case 8:
            bg = `<span class="badge bg-info">${room.roomType.name}</span>`;
            break;
        case 9:
            bg = `<span class="badge bg-primary">${room.roomType.name}</span>`;
            break;
        case 10:
            bg = `<span class="badge bg-warning">${room.roomType.name}</span>`;
            break;
        case 11:
            bg = `<span class="badge bg-secondary">${room.roomType.name}</span>`;
            break;
        case 12:
            bg = `<span class="badge bg-danger">${room.roomType.name}</span>`;
            break;
        case 13:
            bg = `<span class="badge bg-dark">${room.roomType.name}</span>`;
            break;
        default:
            bg = `<span class="badge bg-success">${room.roomType.name}</span>`;
    }
    typeCell.innerHTML = bg;
    row.appendChild(typeCell);

    const actionCell = document.createElement("td");
    actionCell.classList.add('action-' + room.id);
    actionCell.classList.add("align-middle");

    const editButton = document.createElement("button");
    editButton.classList.add("btn", "btn-primary", "mx-2", "btnView-" + room.id);
    editButton.innerHTML = '<i class="fa-solid fa-pen-to-square"></i>&nbsp Edit';

    editButton.addEventListener('click', async () => {
        const editType = document.getElementById('editType');
        await pinDataToSelect(editType, apiRoom + '/type');
        pinRoomToModal(room);
        editModal.show();
    });

    actionCell.appendChild(editButton);
    row.appendChild(actionCell);
}

function pinRoomToModal(room) {
    const editImage = document.getElementById('imgEditRoom');
    const objectURL = apiFile + room.image;
    editImage.setAttribute('src', objectURL);

    const id = document.getElementById('editId');
    id.value = room.id;

    const floor = document.getElementById('editFloor');
    floor.value = room.floor;

    const price = document.getElementById('editPrice');
    price.value = room.price;

    const sale = document.getElementById('editSale');
    sale.value = room.sale;

    const editState = document.getElementById('editState');
    for (const option of editState.options) {
        if (option.textContent === room.state) {
            option.selected = true;
            break;
        }
    }

    const editType = document.getElementById('editType');
    for (const option of editType.options) {
        if (+option.value === room.roomType.id) {
            option.selected = true;
            break;
        }
    }
}

function getRoom(modal) {
    const id = document.getElementById(modal + 'Id').value;
    const floor = document.getElementById(modal + 'Floor').value;
    const price = document.getElementById(modal + 'Price').value;
    const sale = document.getElementById(modal + 'Sale').value;
    const state = document.getElementById(modal + 'State');
    const type = document.getElementById(modal + 'Type');

    const room = {
        id: id,
        floor: floor,
        price: price,
        state: state.options[state.selectedIndex].text,
        hotel: {
            id: hotelId,
        },
        sale: sale,
        roomType: {
            id: type.value,
            name: type.options[type.selectedIndex].text
        }
    }

    return room;
}

function handleUpdate() {
    const btnSaveRoom = document.getElementById('btnSaveRoom');
    btnSaveRoom.onclick = () => {
        swal({
            title: "Are you sure to update this room?",
            icon: "warning",
            buttons: true,
            dangerMode: true,
        }).then((option) => {
            if (option) {
                const room = getRoom('edit');
                const image = document.getElementById('editRoomImage');

                const formData = new FormData();
                formData.append('data', JSON.stringify(room));

                if (image.files.length > 0) {
                    formData.append('file', image.files[0]);
                }
                const token = localStorage.getItem('access_token');

                const options = {
                    method: "PUT",
                    headers: {
                        'Authorization': `Bearer ${token}`,
                    },
                    body: formData
                }

                fetch(apiRoom + '?rid=' + room.id + '&hid=' + hotelId, options)
                    .then(response => response.json())
                    .then(roomResponse => {
                        updateRoomTable(roomResponse.data);
                        editModal.hide();
                        swal(roomResponse.message, {
                            icon: "success",
                        });
                    })
                    .catch(error => {
                        console.error('Error: ', error);
                        swal("Error!", "An error occurred on the server!", "error");
                    });
            }
        })
    }
}

function updateRoomTable(room) {
    const rowCurrent = document.querySelector('.row-' + room.id);
    const tdChilds = rowCurrent.querySelectorAll('td');
    tdChilds.forEach(item => {
        rowCurrent.removeChild(item);
    });

    createRoomRow(room, rowCurrent);
}

function handleBtnAddRoom() {
    const btnAddNew = document.getElementById('btnNew');
    btnAddNew.onclick = () => {
        addModal.show();

        const roomTypeAdd = document.getElementById('addType');
        pinDataToSelect(roomTypeAdd, apiRoom + '/type');
    }
}

function handleCreateRoom() {
    const btnAddRoom = document.getElementById('btnAddRoom');
    btnAddRoom.onclick = () => {
        swal({
            title: "Are you sure to update this?",
            icon: "warning",
            buttons: true,
            dangerMode: true,
        }).then((value) => {
            if (value) {
                const room = getRoom('add');
                const image = document.getElementById('addRoomImage');

                const data = JSON.stringify(room);

                const formData = new FormData();
                formData.append('data', data);

                if (image.files.length > 0) {
                    formData.append('file', image.files[0]);
                }
                const token = localStorage.getItem('access_token');

                const options = {
                    method: "POST",
                    headers: {
                        'Authorization': `Bearer ${token}`,
                    },
                    body: formData
                }

                fetch(apiRoom, options)
                    .then(response => response.json())
                    .then(roomResponse => {
                        clearTable();
                        fetchAllRoomOfHotel(hotelId, currentPage, pageSize);
                        swal("Update successfully!", {
                            icon: "success",
                        });
                    })
                    .catch(error => {
                        console.error('Error: ', error);
                        swal("Error!", "An error occurs in server!", "error");
                    });

                addModal.hide();
            }
        });
    }
}

function clearTable() {
    const roomTable = document.getElementById('roomTable');
    const trChilds = roomTable.querySelectorAll('tr');
    for (let i = 1; i < trChilds.length; i++) {
        roomTable.removeChild(trChilds[i]);
    }
}

function changePageSize(currentPage) {
    const selectPageSize = document.getElementById('pageSize');

    selectPageSize.onchange = () => {
        clearTable();
        fetchAllRoomOfHotel(hotelId, currentPage, selectPageSize.value);
    }
}

function generateRoomPagination(totalPages, currentPage, pageSize) {
    paginationContainer.innerHTML = '';

    if (currentPage > 1) {
        const firstElement = document.createElement('li');
        firstElement.classList.add('page-item')

        const aFirst = document.createElement('a');
        aFirst.href = '#';
        aFirst.classList.add('page-link')

        const spanFirst = document.createElement('span');
        spanFirst.innerHTML = '&laquo;';
        aFirst.appendChild(spanFirst);
        firstElement.appendChild(aFirst);

        firstElement.addEventListener('click', async (e) => {
            e.preventDefault();
            clearTable();
            await fetchAllRoomOfHotel(hotelId, 1, pageSize);

        });
        paginationContainer.appendChild(firstElement);
    }

    for (let i = 1; i <= totalPages; i++) {
        const pageItem = document.createElement('li');
        pageItem.classList.add('page-item')

        const pageLink = document.createElement('a');
        pageLink.href = '#';
        pageLink.classList.add('page-link')
        if (i === currentPage) {
            pageItem.classList.add('active');
        }
        pageLink.textContent = i;

        pageItem.appendChild(pageLink);

        pageItem.addEventListener('click', async (e) => {
            e.preventDefault();
            clearTable();
            await fetchAllRoomOfHotel(hotelId, i, pageSize);
        });

        paginationContainer.appendChild(pageItem);
    }

    if (currentPage < totalPages) {
        const lastElement = document.createElement('li');
        lastElement.classList.add('page-item')

        const aLast = document.createElement('a');
        aLast.href = '#';
        aLast.classList.add('page-link')

        const spanLast = document.createElement('span');
        spanLast.innerHTML = '&raquo;';
        aLast.appendChild(spanLast);
        lastElement.appendChild(aLast);

        lastElement.addEventListener('click', async (e) => {
            e.preventDefault();
            clearTable();
            await fetchAllHotels(keyword, totalPages, pageSize);
        });

        paginationContainer.appendChild(lastElement);
    }
}
