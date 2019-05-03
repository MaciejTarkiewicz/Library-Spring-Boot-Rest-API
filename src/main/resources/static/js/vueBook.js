window.onload = () => {
    new Vue({
        el: "#addBook_vue",
        data: {
            title: '',
            author: '',
            year: '',
            type: '',
            error:false,
            info: ''

        },
        methods: {
            addBook(){
                axios({
                    method: 'post',
                    url: '/api/library/add',
                    data: {title: this.title, author: this.author, year: this.year, type: this.type}
                }).then(function (response) {
                    document.location.replace("/library/all");
                }).catch(err => {
                    if (err.response.status === 409) {
                        this.error = true;
                        this.info = 'Error: Bad data format!';
                    }else{
                        this.error = true;
                        this.info = 'Error: Fill in all fields!';

                    }

                })
            },
            cancel() {
                document.location.replace("/library/all");

            },


        },
    })

};