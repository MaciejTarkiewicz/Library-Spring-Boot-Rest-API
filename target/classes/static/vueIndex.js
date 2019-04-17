window.onload = () => {
    new Vue({
        el: "#index",
        data: {
        },
        methods: {
            register() {
                document.location.replace("/register");
            },
            sign_up() {
                document.location.replace("/login");
            }
        },
    })

}
