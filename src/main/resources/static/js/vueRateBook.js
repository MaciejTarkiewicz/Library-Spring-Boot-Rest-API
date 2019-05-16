window.onload = () => {
    new Vue({
        el: "#rate_vue",
        data: {
            error: false,
            rate:'',
            info:''
        },
        methods: {
            RateBook() {
                axios({
                    method: 'post',
                    url: '/library/book/rate',
                    data: {rate: this.rate}
                }).then(function (response) {
                    document.location.replace('/library/all');
                }).catch(err => {
                    this.error = true;
                    this.info = 'Invalid rating!';
                });

            },

        },


    })

};