window.onload = () => {
    new Vue({
        el: "#library",
        data: {
            title: '',
            author: '',
            productionYear: '',
            type: ''
        },
        methods: {
            show() {
                axios({
                    method: 'get',
                    url: 'library',
                    data: {title: this.title, author: this.author, productionYear : this.productionYear, type : this.type }
                }).then(function (response) {
                    // document.location.replace("/welcome");
                }).catch(err => {
                    alert("Trzoda")
                });
            }
        },
    })

}