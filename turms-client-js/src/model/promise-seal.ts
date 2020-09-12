export default interface PromiseSeal {
    resolve: (value?: any) => void,
    reject: (reason?: any) => void
}