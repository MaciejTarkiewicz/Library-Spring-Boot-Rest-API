window.onload = () => {
    new Vue({
        el: "#addBook_vue",
        data: {
            title: '',
            author: '',
            productionYear: '',
            type: '',
            error:false,
            info: ''

        },
        methods: {
            addBook(){
                if(this.title.trim() === '' || this.author.trim()  === '' || this.productionYear.trim()  === '' || this.type.trim()  === ''){
                    this.error = true;
                    this.info = 'Error: Fill in all fields!';
                    return;
                }
                axios({
                    method: 'post',
                    url: '/api/library/add',
                    data: {title: this.title, author: this.author, productionYear: this.productionYear, type: this.type}
                }).then(function (response) {
                    document.location.replace("/library/all");
                }).catch(err => {
                    this.info = 'Error: Bad data format!';

                })
            },
            cancel() {
                document.location.replace("/library/all");

            },


        },
    })

};