export default class LayoutUtil {
    static get formItemLayout() {
        return {
            labelCol: {span: 4},
            wrapperCol: {span: 18}
        };
    }

    static get formTailLayout() {
        return {
            labelCol: {span: 4},
            wrapperCol: {span: 8, offset: 4}
        };
    }

    static get formItemLayoutWithoutLabel() {
        return {
            wrapperCol: {offset: 4, span: 19}
        };
    }

    static get formItemLayoutWithWideLabel() {
        return {
            labelCol: {span: 6},
            wrapperCol: {span: 16}
        };
    }

    static get formItemLayoutWithWiderLabel() {
        return {
            labelCol: {span: 10},
            wrapperCol: {span: 12}
        };
    }

    static get formItemLayoutWithFullWrapper() {
        return {
            labelCol: {span: 0},
            wrapperCol: {span: 23}
        };
    }
}
