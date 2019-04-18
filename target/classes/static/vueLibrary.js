window.onload = () => {
    new Vue({
        el: "#library",
        data: {
            title: '',
            author: '',
            productionYear: '',
            type: '',
            list : []
        },
        async created() {
            const { data } = await axios.get('/api/library');
            this.title = data[0].title;
        }
    })

}