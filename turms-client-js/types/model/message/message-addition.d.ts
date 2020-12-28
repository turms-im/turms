export default class MessageAddition {
    isMentioned: boolean;
    mentionedUserIds: string[];
    recalledMessageIds: string[];
    constructor(isMentioned: boolean, mentionedUserIds: string[], recalledMessageIds: string[]);
}
