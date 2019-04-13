window.onload = () => {
    new Vue({
        el: "#app",
        data: {
            pages: [
                {text: "Username"},
                {text: "Password"},
                {text: "Confirm Password"},
                {text: "Email"},
                {text: "Confirm Email"}
            ],
            loginPage: [
                {text: "Username"},
                {text: "Password"},
            ]

        },
    })

}