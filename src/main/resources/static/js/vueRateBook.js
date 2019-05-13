window.onload = () => {
    new Vue({
        el: "#rate_vue",
        data: {
            username: '',
            password: '',
            error: false,
            info :'',
        },
        methods: {
            rate() {
                axios({
                    method: 'post',
                    url: '/library/book/rate',
                    data: {rat: this.rat}
                }).then(function (response) {
                    document.location.replace("/library/user?username=" + username);
                }).catch(err => {
                    // if (err.response.status === 400) {
                    //     this.error = true;
                    //     this.info = 'Invalid rating!';
                    // }
                });

            },

        },

    })

};