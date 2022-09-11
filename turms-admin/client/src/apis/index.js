import MemberApis from './member-apis';
import OnlineUserApis from './online-user-apis';

// TODO: Consolidate all APIs
export default function createApis(globalProperties) {
    return {
        ...MemberApis,
        ...OnlineUserApis,
        ...globalProperties
    };
}