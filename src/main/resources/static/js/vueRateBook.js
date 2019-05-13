window.onload = () => {
    new Vue({
        el: "#rate_vue",
        data: {
            username: '',
            password: '',
            error: false,
            info :'',
            l:'',
            rat:''
        },
        methods: {
            rate(id) {
                axios({
                    method: 'post',
                    url: '/library/book/rate?id=' + id,
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
        async created() {
            const { data } = await axios.get('api/library/book/rate"');
            this.l = data;
            console.log(this.l)

        }

    })

};