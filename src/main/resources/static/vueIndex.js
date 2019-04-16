window.onload = () => {
    new Vue({
        el: "#index",
        data: {
        },
        methods: {
            register() {
                axios({
                    method: 'post',
                    url: '',
                }).then(function (response) {
                    document.location.replace("/register");
                });
            },
            sign_up() {
              axios({
                    method: 'post',
                    url: '',
                }).then(function (response) {
                    document.location.replace("/login");
                });
            }
        },
    })



}
