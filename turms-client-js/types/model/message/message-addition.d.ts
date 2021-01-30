export default class MessageAddition {
    readonly isMentioned: boolean;
    readonly mentionedUserIds: string[];
    readonly recalledMessageIds: string[];
    constructor(isMentioned: boolean, mentionedUserIds: string[], recalledMessageIds: string[]);
}
