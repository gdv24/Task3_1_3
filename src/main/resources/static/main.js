function fetchTableCurrentUser(id) {
    fetch("http://localhost:8080/api/admin/currentuser", {method: 'GET'}).then(function (response) {
        return response.json();
    })
        .then(function (data) {
            console.log(data);
            document.getElementById(id).innerHTML = `
            <tr>
                <td>${data.id}</td>
                <td>${data.name}</td>
                <td>${data.username}</td>
                <td>${data.age}</td>
                <td>${data.email}</td>
                <td>${data['roles'].map(function (r) {
                return r.name
            }).join('')}</td>
            </tr>
            `
        })
        .catch(function (err) {
            console.log(err);
        });
}

function fetchUserDeleteId(id) {
    fetch("http://localhost:8080/api/admin/" + id, {method: 'GET'}).then(function (response) {
        return response.json();
    })
        .then(function (data) {

            let id = data.id;
            let name = data.name;
            let username = data.username;
            let age = data.age;
            let email = data.email;
            let password = data.password;
            let roles = data['roles'].map(r => r.name).join('');

            $("#iddf").val(id);
            $("#namedf").val(name);
            $("#usernamedf").val(username);
            $("#agedf").val(age);
            $("#passworddf").val(password);
            $("#emaildf").val(email);
            $("#roledf").val(roles);

        })
        .catch(function (err) {
            console.log(err);
        });
}

function fetchUserEditId(id) {
    fetch("http://localhost:8080/api/admin/" + id, {method: 'GET'}).then(function (response) {
        return response.json();
    })
        .then(function (data) {

            let id = data.id;
            let name = data.name;
            let username = data.username;
            let age = data.age;
            let email = data.email;
            let password = data.password;
            let roles = data['roles'].map(r => r.name).join('');

            $("#idEdit").val(id);
            $("#nameEdit").val(name);
            $("#usernameEdit").val(username);
            $("#ageEdit").val(age);
            $("#passwordEdit").val(password);
            $("#emailEdit").val(email);
            $("#roleEdit").val(roles);

        })
        .catch(function (err) {
            console.log(err);
        });
}

function fetchTableAllUser(id2) {
    fetch("http://localhost:8080/api/admin", {method: 'GET'}).then(function (response) {
        return response.json();
    })
        .then(function (data) {
            if (data.length > 0) {

                let temp = " ";

                data.forEach((u) => {
                    temp += "<tr>";
                    temp += "<td>" + u.id + "</td>";
                    temp += "<td>" + u.name + "</td>";
                    temp += "<td>" + u.username + "</td>";
                    temp += "<td>" + u.age + "</td>";
                    temp += "<td>" + u.email + "</td>";
                    temp += "<td>" + u['roles'].map(r => r.name + ' ').join('') + "</td>";
                    temp += "<td>" + "<button type='button' class='btn btn-primary' data-toggle='modal' data-target ='#editModal' " +
                        " data-id = " + u.id + "  > Edit </button>" + "</td>";
                    temp += "<td>" + "<button type='button' class='btn btn-danger' data-toggle='modal' data-target ='#deleteModal' " +
                        " data-id = " + u.id + "  > Delete </button>" + "</td>";
                    temp += "</tr>";
                })
                document.getElementById(id2).innerHTML = temp;
            }
        })
        .catch(function (err) {
            console.log(err);
        });
}

fetchTableAllUser("tableAllUser");
fetchTableCurrentUser("tableCurrentUser");

$('#deleteModal').on('show.bs.modal', function (event) {
    var button = $(event.relatedTarget)
    var id = button.data('id')
    var modal = $(this)
    modal.find('.modal-footer input').val(id)
    fetchUserDeleteId(id)
})

function deleteUser(deleteId) {

    const delId = deleteId.value;
    fetch('http://localhost:8080/api/admin/' + delId, {
        method: 'DELETE',
    })
         .then(res => fetchTableAllUser("tableAllUser"))
    $(".modal").modal('hide');

}

$('#editModal').on('show.bs.modal', function (event) {
    var button = $(event.relatedTarget)
    var id = button.data('id')
    var modal = $(this)
    modal.find('.modal-footer input').val(id)
    fetchUserEditId(id)

})

function editUser(editId) {

    let user = new Object();
    user.id = $("#idEdit").val();
    user.name = $("#nameEdit").val();
    user.username = $("#usernameEdit").val();
    user.age = $("#ageEdit").val();
    user.password = $("#passwordEdit").val();
    user.email = $("#emailEdit").val();
    user.roles = $("#roleEdit").val();

    const edId = editId.value;
    fetch('http://localhost:8080/api/admin/', {
        method: 'PUT',
        headers:{
            'Content-Type': 'application/json; charset=utf-8'
        },
        body: JSON.stringify(user)
    })
        // .then(res => res.text()) // or res.json()
        .then(res => console.log(res))
        .then(res => fetchTableAllUser("tableAllUser"))
    $(".modal").modal('hide');

}

function newUser() {

    let user = new Object();
    user.name = $("#nameNew").val();
    user.username = $("#usernameNew").val();
    user.age = $("#ageNew").val();
    user.password = $("#passwordNew").val();
    user.email = $("#emailNew").val();
    user.roles = $("#roleNew").val();

    fetch('http://localhost:8080/api/admin/', {
        method: 'POST',
        headers:{
            'Content-Type': 'application/json; charset=utf-8'
        },
        body: JSON.stringify(user)
    })
        .then(res => console.log(res))
        .then(res => fetchTableAllUser("tableAllUser"))
        document.getElementById('addForm').reset();
}