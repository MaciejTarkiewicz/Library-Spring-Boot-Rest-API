window.onload = () => {
    new Vue({
        el: "#login_vue",
        data: {
            username: '',
            password: '',
            error: false,
            info :''
        },
        methods: {
            signUp() {
                axios({
                    method: 'post',
                    url: '/api/login',
                    data: {username: this.username, password: this.password}
                }).then(function (response) {
                    document.location.replace("/library/all");
                }).catch(err => {
                    if (err.response.status === 400) {
                        this.error = true;
                        this.info = 'Error: Invalid Username or Password!';
                    }
                });

            },
            home(){
                document.location.replace("/");
            }
        },
    })

};
