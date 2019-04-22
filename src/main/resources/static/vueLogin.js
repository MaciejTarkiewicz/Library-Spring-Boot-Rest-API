window.onload = () => {
    new Vue({
        el: "#login",
        data: {
            username: '',
            password: '',
        },
        methods: {
            sign_up() {
                axios({
                    method: 'post',
                    url: 'login',
                    data: {username: this.username, password: this.password}
                }).then(function (response) {
                    document.location.replace("/library");
                }).catch(err => {
                    alert("Invalid username or password!")
                });
            },
            reg(){
                document.location.replace("/register");
            }
        },
    })

};
