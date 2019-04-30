window.onload = () => {
    new Vue({
        el: "#index_vue",
        data: {
        },
        methods: {
            register() {
                document.location.replace("/register");
            },
            signUp() {
                document.location.replace("/login");
            }
        },
    })

};