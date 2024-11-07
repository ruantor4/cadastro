const formulario = document.querySelector("form");
const INome = document.querySelector(".name");
const IEmail = document.querySelector(".email");
const IPassword = document.querySelector(".password");
const IPhone = document.querySelector(".phone");

function cadastrar() {
  fetch("http://localhost:8080/users", {
    headers: {
      "Accept": "application/json",
      "Content-Type": "application/json"
    },
    method: "POST",
    body: JSON.stringify({
      name: INome.value,
      email: IEmail.value,
      password: IPassword.value,
      phone: IPhone.value
    })
  })
    .then(function (res) {
      console.log(res);
    })
    .catch(function (res) {
      console.log(res);
    });
}

function limpar () {
    INome.value = ""
    IEmail.value = ""
    IPassword.value = ""
    IPhone.value = ""
}

formulario.addEventListener("submit", function (event) {
  event.preventDefault();

    cadastrar()
    limpar()
});
