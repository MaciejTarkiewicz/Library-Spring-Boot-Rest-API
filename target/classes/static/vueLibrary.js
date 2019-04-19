window.onload = () => {
    new Vue({
        el: "#library",
        data: {
            title: '',
            author: '',
            productionYear: '',
            type: '',
            l: '',
            k: ''
        },
        methods: {
            AddBook() {
                document.location.replace("/library/add");
            },
            logout() {
                document.location.replace("/");
            },
            delete() {
                document.location.replace("/library");

            },
        },
        async created() {

            const { data } = await axios.get('/api/library/${k}');
            this.l = data;
            console.log(this.l);
        }

    })

};