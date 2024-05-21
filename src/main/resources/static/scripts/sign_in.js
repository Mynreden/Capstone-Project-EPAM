let canLogin1 = false
let canLogin2 = false

function toogle() {
    let sign_in = document.getElementById("sign_in")
    let sign_up = document.getElementById("sign_up")

    if (sign_in.style.display === "none") {
        sign_in.style.display = "flex";
        sign_up.style.display = "none";
    } else {
        sign_in.style.display = "none";
        sign_up.style.display = "flex";
    }

}

function checkUsername() {
    let username = document.getElementById("username").value
    let checker = document.getElementById("usernameChecker")
    if (username.length < 5) {
        checker.innerHTML = "Username must be at least 5 characters long"

    }
    else {
        checker.innerHTML = ""
        canLogin1 = true
    }
}

function checkPassword() {
    let password = document.getElementById("password").value
    let checker = document.getElementById("passwordChecker")
    if (password.length < 8) {
        checker.innerHTML = "Password must be at least 8 characters long"

    }
    else if (!containInt(password)) {
        checker.innerHTML = "Password must contain at least one number"

    }
    else {
        checker.innerHTML = ""
        canLogin2 = true
    }
}

let containInt = (str) => {
    for (let i = 0; i < str.length; i++) {
        if (str[i] >= '0' && str[i] <= '9') {
            return true
        }
    }
    return false
}