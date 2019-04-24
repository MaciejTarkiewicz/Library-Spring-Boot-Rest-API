window.onload = () => {
    new Vue({
        el: "#index",
        data: {
        },
        methods: {
            register_index() {
                document.location.replace("/register");
            },
            sign_up_index() {
                document.location.replace("/login");
            }
        },
    })

};
