const editForm = document.querySelector('.editForm')
const btnSub = document.querySelector('.subBTN')

const deleteForm = document.querySelector('.deleteForm')
const btnDel = document.querySelector('.delBTN')

const url = "http://localhost:8080/api/users";

const addPostForm = document.querySelector('.addForm');


async function usersTable() {
    const response = await fetch(url);
    const data = await response.json();
    let output = '';

    data.forEach((user) => {

        output += "<tr id=" + 'dataId' + user.id + ">" +
            "<td >" + user.id + "</td>\n" +
            "    <td >" + user.name + "</td>\n" +
            "    <td>" + user.lastname + "</td>\n" +
            "    <td>" + user.age + "</td>\n" +
            "    <td>" + user.email + "</td>\n" +
            "    <td>" + user.stringRole + "</td>\n" +
            "    <td><button type=\"button\" class=\"btn btn-info\" data-toggle=\"modal\" data-target=\"#editModal\">Edit</button></td>\n" +
            "    <td><button type=\"button\" class=\"btn btn-danger\" data-toggle=\"modal\" data-target=\"#deleteModal\">Edit</button></td><tr>";
    })

    document.getElementById("usersTable").innerHTML = output;

    data.forEach(u => {
        const btnEdit = document.querySelector(`#dataId${u.id} .btn-info`);
        btnEdit.addEventListener('click', () => {

            editForm.id.value = u.id
            editForm.name.value = u.name
            editForm.lastname.value = u.lastname
            editForm.age.value = u.age
            editForm.email.value = u.email
            editForm.password.value = u.password
         })


        const btnDelete = document.querySelector(`#dataId${u.id} .btn-danger`);
        btnDelete.addEventListener('click', () => {

            deleteForm.id.value = u.id
            deleteForm.name.value = u.name
            deleteForm.lastname.value = u.lastname
            deleteForm.age.value = u.age
            deleteForm.email.value = u.email
            deleteForm.password.value = u.password
        })
    })
}

usersTable().then()



btnSub.addEventListener('click', async (e) =>{
    e.preventDefault();
    let selected1 = Array.from(editForm.roles.options)
        .filter(option => option.selected)
        .map(option => option.value);
    await fetch(url, {
        method : 'PATCH',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            id: document.getElementById('editId').value,
            name: document.getElementById('editName').value,
            lastname: document.getElementById('editLastname').value,
            age: document.getElementById('editAge').value,
            email: document.getElementById('editEmail').value,
            password: document.getElementById('editPassword').value,
            roles: selected1
        })
    }).then(res => {
        res.json()
        usersTable()
    })
})

btnDel.addEventListener('click', async (e) => {
    e.preventDefault();
    let id = document.getElementById('deleteId').value;
    let delURL = url + '/' + id;
    await fetch(delURL,{
        method: 'DELETE'
    }).then((res) => {
        res.json()
        usersTable()
    })

})

addPostForm.addEventListener('submit', async (e) => {
    e.preventDefault();
    let selected = Array.from(addPostForm.roles.options)
        .filter(option => option.selected)
        .map(option => option.value);

    await fetch("http://localhost:8080/api/users", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            name: document.getElementById('inputFirstname').value,
            lastname: document.getElementById('inputLastname').value,
            age: document.getElementById('inputAge').value,
            email: document.getElementById('inputEmail').value,
            password: document.getElementById('inputPassword').value,
            roles: selected
        })
    })
        .then( () => {
            document.getElementById('inputFirstname').value = '';
            document.getElementById('inputLastname').value = '';
            document.getElementById('inputAge').value = '';
            document.getElementById('inputEmail').value = '';
            document.getElementById('inputPassword').value = '';
            selected = '';
            usersTable()
        })

})







