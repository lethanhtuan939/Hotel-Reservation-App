const apiReservation = 'http://localhost:8080/api/v1/reservation';
const apiUsers = 'http://localhost:8080/api/v1/users/';

const STATE = {
    ACCEPT: 'ĐÃ CHẤP NHẬN',
    DONE: 'ĐÃ XONG',
    CANCEL: 'ĐÃ HỦY',
    PENDING: 'ĐANG CHỜ'
}

let totalPages = 0;
let currentPage = 1;
let pageSize = 5;
let keyword = '';

const paginationContainer = document.querySelector(".pagination");

start();

function start() {
    fetchAllReservation(keyword, currentPage, pageSize);
}

function fetchAllReservation(keyword, currentPage, pageSize) {
    fetch(apiReservation + '?p=' + currentPage + '&keyword=' + keyword + '&s=' + pageSize)
        .then(response => response.json())
        .then(reservation => {
            totalPages = reservation.totalPages;
            currentPage = reservation.currentPage;
            pageSize = reservation.pageSize;
            keyword = reservation.keyword;

            renderReservation(reservation.data);
            generatePagination(totalPages, currentPage, keyword, pageSize);
            changeStatus(currentPage, pageSize);
        })
}

function createRowReservation(reservation, row) {
    const idCell = document.createElement('td');
    idCell.classList.add('id-' + reservation.id);
    idCell.classList.add("align-middle");
    idCell.textContent = reservation.id;
    row.appendChild(idCell);

    const customerCell = document.createElement('td');
    customerCell.classList.add('id-' + reservation.id);
    customerCell.classList.add("align-middle");
    customerCell.textContent = reservation.user.name;
    row.appendChild(customerCell);

    const hotelCell = document.createElement("td");
    hotelCell.classList.add('name-' + reservation.id);
    hotelCell.classList.add("align-middle");
    hotelCell.textContent = reservation.room.hotel.name;
    row.appendChild(hotelCell);

    const roomCell = document.createElement("td");
    roomCell.classList.add('name-' + reservation.id);
    roomCell.classList.add("align-middle");
    roomCell.textContent = reservation.room.name;
    row.appendChild(roomCell);

    const roomTypeCell = document.createElement("td");
    roomTypeCell.classList.add('name-' + reservation.id);
    roomTypeCell.classList.add("align-middle");
    let bg1 = '';
    switch (reservation.room.roomType.id) {
        case 7:
            bg1 = `<span class="badge bg-success">${reservation.room.roomType.name}</span>`;
            break;
        case 8:
            bg1 = `<span class="badge bg-info">${reservation.room.roomType.name}</span>`;
            break;
        case 9:
            bg1 = `<span class="badge bg-primary">${reservation.room.roomType.name}</span>`;
            break;
        case 10:
            bg1 = `<span class="badge bg-warning">${reservation.room.roomType.name}</span>`;
            break;
        case 11:
            bg1 = `<span class="badge bg-secondary">${reservation.room.roomType.name}</span>`;
            break;
        case 12:
            bg1 = `<span class="badge bg-danger">${reservation.room.roomType.name}</span>`;
            break;
        case 13:
            bg1 = `<span class="badge bg-dark">${reservation.room.roomType.name}</span>`;
            break;
        default:
            bg1 = `<span class="badge bg-success">${reservation.room.roomType.name}</span>`;
    }
    roomTypeCell.innerHTML = bg1;
    row.appendChild(roomTypeCell);

    const dayStartCell = document.createElement("td");
    dayStartCell.classList.add('name-' + reservation.id);
    dayStartCell.classList.add("align-middle");
    dayStartCell.textContent = reservation.dayStart;
    row.appendChild(dayStartCell);

    const dayEndCell = document.createElement("td");
    dayEndCell.classList.add('name-' + reservation.id);
    dayEndCell.classList.add("align-middle");
    dayEndCell.textContent = reservation.dayEnd;
    row.appendChild(dayEndCell);

    const priceCell = document.createElement("td");
    priceCell.classList.add('name-' + reservation.id);
    priceCell.classList.add("align-middle");
    priceCell.textContent = reservation.price + ' $';
    row.appendChild(priceCell);

    const paymentMethodCell = document.createElement("td");
    paymentMethodCell.classList.add('name-' + reservation.id);
    paymentMethodCell.classList.add("align-middle");
    paymentMethodCell.innerHTML = reservation.paymentMethod === 'TRẢ BẰNG TIỀN MẶT'
        ? `<span class="badge bg-success">${reservation.paymentMethod}</span>`
        : `<span class="badge bg-info">${reservation.paymentMethod}</span>`;
    row.appendChild(paymentMethodCell);

    const statusCell = document.createElement("td");
    statusCell.classList.add('name-' + reservation.id);
    statusCell.classList.add("align-middle");
    let bg = '';

    switch (reservation.status) {
        case STATE.PENDING:
            bg = `<span class="badge bg-warning">${reservation.status}</span>`;
            break;
        case STATE.ACCEPT:
            bg = `<span class="badge bg-success">${reservation.status}</span>`;
            break;
        case STATE.CANCEL:
            bg = `<span class="badge bg-danger">${reservation.status}</span>`;
            break;
        case STATE.DONE:
            bg = `<span class="badge bg-info">${reservation.status}</span>`;
            break;
        default:
            bg = `<span class="badge bg-black">${reservation.status}</span>`;
    }

    statusCell.innerHTML = bg;
    statusCell.innerHTML
    row.appendChild(statusCell);

    const actionCell = document.createElement("td");
    actionCell.classList.add('action-' + reservation.id);
    actionCell.classList.add("align-middle");

    fetch(apiUsers + '?e=' + localStorage.getItem('email'))
        .then(response => response.json())
        .then(user => {
            if (+user.data.roles[0].id === 1) {
                if (reservation.status === STATE.PENDING) {
                    const btnAccept = document.createElement("button");
                    btnAccept.classList.add("btn", "btn-success", "mx-2", "btnAccept-" + reservation.id);
                    // btnAccept.appendChild('')
                    btnAccept.textContent = "Accept";
                    btnAccept.onclick = () => {
                        changeStatusReservation(reservation.id, STATE.ACCEPT);
                    }

                    const btnCancel = document.createElement("button");
                    btnCancel.classList.add("btn", "btn-danger", "btnCancel-" + reservation.id);
                    btnCancel.textContent = "Cancel";
                    btnCancel.onclick = () => {
                        changeStatusReservation(reservation.id, STATE.CANCEL);
                    }

                    actionCell.appendChild(btnAccept);
                    actionCell.appendChild(btnCancel)
                    row.appendChild(actionCell);
                } else if (reservation.status === STATE.ACCEPT) {
                    const btnDone = document.createElement("button");
                    btnDone.classList.add("btn", "btn-info", "mx-2", "btnDone-" + reservation.id);
                    btnDone.textContent = "Mark as done";
                    btnDone.onclick = () => {
                        changeStatusReservation(reservation.id, STATE.DONE);
                    }

                    actionCell.appendChild(btnDone);
                    row.appendChild(actionCell);
                }
            }
        })
}

function renderReservation(reservations) {
    reservations.forEach(reser => {
        displayReservation(reser);
    });
}

function displayReservation(reservation) {
    const reservationTable = document.getElementById('reservationTable');

    const row = document.createElement("tr");
    const idRow = 'row-' + reservation.id;
    row.classList.add(idRow);

    createRowReservation(reservation, row);

    reservationTable.appendChild(row);
}

function changeStatusReservation(id, status) {
    const token = localStorage.getItem('access_token');

    const options = {
        method: "POST",
        headers: {
            'Authorization': `Bearer ${token}`,
        }
    }

    fetch(apiReservation + '/state?id=' + id + '&state=' + status, options)
        .then(response => response.json())
        .then(reservation => {
            updateTableReservation(reservation.data);
        })
}

function updateTableReservation(reservation) {
    const rowCurrent = document.querySelector('.row-' + reservation.id);
    const tdChilds = rowCurrent.querySelectorAll('td');
    tdChilds.forEach(item => {
        rowCurrent.removeChild(item);
    });

    createRowReservation(reservation, rowCurrent);
}

function clearTableResrvation() {
    const reservationTable = document.getElementById('reservationTable');
    const trChilds = reservationTable.querySelectorAll('tr');

    for (let i = trChilds.length - 1; i > 0; i--) {
        reservationTable.removeChild(trChilds[i]);
    }
}


function generatePagination(totalPages, currentPage, keyword, pageSize) {

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
            clearTableResrvation();
            await fetchAllReservation(keyword, 1, pageSize);
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
            clearTableResrvation();
            await fetchAllReservation(keyword, i, pageSize);
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
            clearTableResrvation();
            await fetchAllReservation(keyword, totalPages, pageSize);
        })

        paginationContainer.appendChild(lastElement);
    }
}

function changeStatus(currentPage, pageSize) {
    const selectStatus = document.getElementById('status');

    selectStatus.onchange = () => {
        clearTableResrvation();
        fetchAllReservation(selectStatus.value, currentPage, pageSize);
    }
}