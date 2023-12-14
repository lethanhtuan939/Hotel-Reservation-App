const apiHotel = 'http://localhost:8080/api/v1/hotels';
const apiFile = 'http://localhost:8080/api/v1/file/';
const apiLocation = 'http://localhost:8080/api/v1/locations';
const apiUser = 'http://localhost:8080/api/v1/users/';

const addModal = new bootstrap.Modal(document.getElementById('addModal'));

const paginationContainer = document.querySelector(".pagination");
let totalPages = 0;
let currentPage = 1;
let pageSize = 5;
let keyword = '';

start();

function start() {
    check();
    fetchAllHotels(keyword, currentPage, pageSize);
    handleBtnAddNew();
    handleCreate();
}

function check() {
    const btnNew = document.getElementById('btnNew');

    fetch(apiUser + '?e=' + localStorage.getItem('email'))
        .then(response => response.json())
        .then(user => {
            if (+user.data.roles[0].id === 1) {
                btnNew.style.display = 'block';
            } else {
                btnNew.style.display = 'none';
            }
        })
}

function bindingLocation(pageSize) {
    const locationSelect = document.getElementById('locationSelect');

    pinDataToSelect(locationSelect);
    locationSelect.onchange = () => {
        const value = locationSelect.options[locationSelect.selectedIndex].text;
        callApiWithSearch(value, pageSize);
    }
}

function fetchAllHotels(keyword, currentPage, pageSize) {
    fetch(apiHotel + '?keyword=' + keyword + '&p=' + currentPage + '&s=' + pageSize)
        .then((response) => response.json())
        .then((hotels) => {
            totalPages = hotels.totalPages;
            currentPage = hotels.currentPage;
            pageSize = hotels.pageSize;
            keyword = hotels.keyword;

            renderHotels(hotels.data);

            handleSearch(pageSize);
            generatePagination(totalPages, currentPage, keyword, pageSize);
            changePageSize(currentPage, keyword);
            bindingLocation(pageSize);
        })
}

function renderHotels(hotels) {
    hotels.forEach(hotel => {
        displayHotel(hotel);
    });
}

function createRow(hotel, row) {
    const idCell = document.createElement("td");
    idCell.classList.add('id-' + hotel.id);
    idCell.classList.add("align-middle");
    idCell.textContent = hotel.id;
    row.appendChild(idCell);

    const nameCell = document.createElement("td");
    nameCell.classList.add('name-' + hotel.id);
    nameCell.classList.add("align-middle");
    nameCell.textContent = hotel.name;
    row.appendChild(nameCell);

    const acreageCell = document.createElement("td");
    acreageCell.classList.add('gender-' + hotel.id, "align-middle");
    acreageCell.textContent = hotel.acreage + ' m²';
    row.appendChild(acreageCell);

    const locationCell = document.createElement("td");
    locationCell.classList.add('location-' + hotel.id);
    locationCell.classList.add("align-middle");
    locationCell.textContent = hotel.location.name;
    row.appendChild(locationCell);

    const rateCell = document.createElement("td");
    rateCell.classList.add('rate-' + hotel.id);
    rateCell.classList.add("align-middle");

    const starIcon = document.createElement("i");
    starIcon.classList.add("fa-solid", "fa-star");
    starIcon.style.color = '#facc15';
    const ratingText = document.createTextNode(hotel.rating);

    rateCell.appendChild(ratingText);
    rateCell.appendChild(starIcon);
    row.appendChild(rateCell);

    const imageCell = document.createElement("td");
    imageCell.classList.add("align-middle");
    imageCell.classList.add('image-' + hotel.id);
    row.appendChild(imageCell);

    const objectURL = apiFile + hotel.image;
    const imgElement = document.createElement("img");
    imgElement.classList.add('img-' + hotel.id);
    imgElement.setAttribute("src", objectURL);
    imgElement.setAttribute("width", "80");
    imgElement.setAttribute("height", "80");
    imageCell.appendChild(imgElement);

    const enabled = document.createElement("td");
    enabled.classList.add('enabled-' + hotel.id);
    enabled.classList.add("align-middle");
    enabled.innerHTML = hotel.active
        ? '<span class="badge bg-success">Enabled</span>'
        : '<span class="badge bg-danger">Disabled</span>';
    row.appendChild(enabled);

    fetch(apiUser + '?e=' + localStorage.getItem('email'))
        .then(response => response.json())
        .then(user => {
            if (+user.data.roles[0].id === 1) {
                const actionCell = document.createElement("td");

                actionCell.classList.add('action-' + hotel.id);
                actionCell.classList.add("align-middle");

                const viewButton = document.createElement("button");
                viewButton.classList.add("btn", "btn-info", "mx-2", "btnView-" + hotel.id);
                viewButton.innerHTML = '<i class="fa-solid fa-eye"></i>';
                viewButton.onclick = () => {
                    window.location.href = 'hotel-detail.html?id=' + hotel.id;
                }
                actionCell.appendChild(viewButton);
                row.appendChild(actionCell);
            }
        })
}

function displayHotel(hotel) {
    const hotelTable = document.getElementById('hotelTable');

    const row = document.createElement("tr");
    const idRow = 'row-' + hotel.id;
    row.classList.add(idRow);

    createRow(hotel, row);

    hotelTable.appendChild(row);
}

function pinHotelToModal(hotel) {
    const editImage = document.getElementById('editImg');
    const objectURL = apiFile + hotel.image;
    editImage.setAttribute('src', objectURL);

    const id = document.getElementById('editId');
    id.value = hotel.id;

    const name = document.getElementById('editName');
    name.value = hotel.name;

    const editAcreage = document.getElementById('editAcreage');
    editAcreage.value = hotel.acreage;

    const editRate = document.getElementById('editRate');
    editRate.value = hotel.rating;

    const editLocation = document.getElementById('editLocation');
    for (const option of editLocation.options) {
        if (+option.value === hotel.location.id) {
            option.selected = true;
            break;
        }
    }

    const editState = document.getElementById('editState');
    for (const option of editState.options) {
        let value = stringToBool(option.value)
        if (value === hotel.active) {
            option.selected = true;
            break;
        }
    }
}

function stringToBool(val) {
    return (val + '').toLowerCase() === 'true';
}

function clearTable() {
    const hotelTable = document.getElementById('hotelTable');
    const trChilds = hotelTable.querySelectorAll('tr');
    for (let i = 1; i < trChilds.length; i++) {
        hotelTable.removeChild(trChilds[i]);
    }
}

function generatePagination(totalPages, currentPage, keyword, pageSize) {
    document.querySelector('#search').value = keyword;

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
            await fetchAllHotels(keyword, 1, pageSize);
        })

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
            await fetchAllHotels(keyword, i, pageSize);
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
        })

        paginationContainer.appendChild(lastElement);
    }
}

function handleBtnAddNew() {
    const btnNew = document.getElementById('btnNew');
    btnNew.addEventListener('click', () => {
        addModal.show();
        const newLocationSelect = document.getElementById('newLocation');
        pinDataToSelect(newLocationSelect);
    })
}

async function pinDataToSelect(newLocationSelect) {
    await fetch(apiLocation)
        .then((response) => response.json())
        .then(res => {
            handlePinData(res.data, newLocationSelect);
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

function getHotelModal(modal) {
    const id = document.getElementById(modal + 'Id').value;
    const name = document.getElementById(modal + 'Name').value;
    const acreage = document.getElementById(modal + 'Acreage').value;
    const location = document.getElementById(modal + 'Location');
    const state = document.getElementById(modal + 'State').value;
    const rate = document.getElementById(modal + 'Rate').value;

    const hotel = {
        id: id,
        name: name,
        acreage: acreage,
        location: {
            id: location.value,
            name: location.options[location.selectedIndex].text
        },
        active: state,
        rating: rate
    }

    return hotel;
}

function handleCreate() {
    const btnSubmitNew = document.getElementById('btnSubmitNew');

    btnSubmitNew.addEventListener('click', () => {
        const hotel = getHotelModal('new');
        const iamge = document.getElementById('newImage');

        const data = JSON.stringify(hotel);

        const formData = new FormData();
        formData.append('data', data);

        if (iamge.files.length > 0) {
            formData.append('file', iamge.files[0]);
        }

        swal({
            title: "Are you sure to add this hotel?",
            icon: "warning",
            buttons: true,
            dangerMode: true,
        }).then((option) => {
            if (option) {
                const token = localStorage.getItem('access_token');

                const options = {
                    method: "POST",
                    headers: {
                        'Authorization': `Bearer ${token}`,
                    },
                    body: formData
                }

                fetch(apiHotel, options)
                    .then(response => response.json())
                    .then(hotelResponse => {
                        addModal.hide();
                        clearTable();
                        fetchAllHotels(keyword, currentPage, pageSize);
                    })
                    .catch(error => {
                        console.error('Error: ', error);
                        swal("Error!", "An error occurs in server!", "error");
                    });

                swal("Update successfully!", {
                    icon: "success",
                });
            }
        })
    })
}

function callApiWithSearch(value, pageSize) {
    clearTable();
    fetchAllHotels(value, 1, pageSize);
}

function handleSearch(pageSize) {
    const inputSearch = document.getElementById('search');
    let inputTimer;

    inputSearch.oninput = () => {
        clearTimeout(inputTimer);
        const inputValue = inputSearch.value;
        callApiWithSearch(inputValue, pageSize);
    }
}

function changePageSize(currentPage, keyword) {
    const selectPageSize = document.getElementById('pageSize');

    selectPageSize.onchange = () => {
        clearTable();
        fetchAllHotels(keyword, currentPage, selectPageSize.value);
    }
}





