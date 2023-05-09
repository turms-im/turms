interface Layout {
    labelCol?: {
        span: number,
        offset?: number
    };
    wrapperCol?: {
        span: number,
        offset?: number
    };
}

export default class LayoutUtil {
    static get formItemLayout(): Layout {
        return {
            labelCol: { span: 4 },
            wrapperCol: { span: 18 }
        };
    }

    static get formTailLayout(): Layout {
        return {
            labelCol: {span: 4},
            wrapperCol: {span: 8, offset: 4}
        };
    }

    static get formItemLayoutWithoutLabel(): Layout {
        return {
            wrapperCol: {offset: 4, span: 19}
        };
    }

    static get formItemLayoutWithWideLabel(): Layout {
        return {
            labelCol: {span: 6},
            wrapperCol: {span: 16}
        };
    }

    static get formItemLayoutWithWiderLabel(): Layout {
        return {
            labelCol: {span: 10},
            wrapperCol: {span: 12}
        };
    }

    static get formItemLayoutWithFullWrapper(): Layout {
        return {
            labelCol: {span: 0},
            wrapperCol: {span: 23}
        };
    }
}
