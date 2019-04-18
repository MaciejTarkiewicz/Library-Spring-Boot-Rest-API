window.onload = () => {
    new Vue({
        el: "#library",
        data: {
            title: '',
            author: '',
            productionYear: '',
            type: '',
            l: ''
        },
        async created() {
            const { data } = await axios.get('/api/library');
           /* this.title = data[0].title;
            this.author = data[0].author;
            this.productionYear = data[0].productionYear;
            this.type = data[0].type;*/
            this.l = data;
            console.log(this.l);
        }
    })

}