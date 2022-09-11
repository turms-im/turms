export default {
    fetchOnlineUsers() {
        return this.$http.get(this.$rs.apis.userOnline)
            .then(response => response.data.data.total);
    }
};