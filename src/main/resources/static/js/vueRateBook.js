window.onload = () => {
    new Vue({
        el: "#rate_vue",
        data: {
            error: false,
            rat:'',
            info:''
        },
        methods: {
            rate() {
                axios({
                    method: 'post',
                    url: '/library/book/rate',
                    data: {rat: this.rat}
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