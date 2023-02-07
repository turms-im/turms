import type {ComponentCustomProperties} from '@vue/runtime-core';

interface Api {
    fetchOnlineUsers(): Promise<number>;
}

export default {
    fetchOnlineUsers(): Promise<number> {
        return this.$http.get(this.$rs.apis.userOnline)
            .then(response => response.data.data.total);
    }
} as ThisType<ComponentCustomProperties & Api>;