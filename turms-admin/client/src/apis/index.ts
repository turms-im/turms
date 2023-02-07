import type {ComponentCustomProperties} from '@vue/runtime-core';
import MemberApis from './member-apis';
import OnlineUserApis from './online-user-apis';

// TODO: Consolidate all APIs
export default function createApis(globalProperties: ComponentCustomProperties & Record<string, any>): ComponentCustomProperties & Record<string, any> {
    return Object.assign(globalProperties, MemberApis, OnlineUserApis);
}