
function fetchData(id) {
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
                <td>${data['roles'].map(r => r.name+" ").join('')}</td>
            </tr>
            `
        })
        .catch(function (err) {
            console.log(err);
        });
}
fetchData("tbody_user");