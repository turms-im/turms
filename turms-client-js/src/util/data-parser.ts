interface EnumInfo {
    nameToValue: Record<string | number, number>,
    allNames: Set<string | number>,
}

const ENUM_TYPE_TO_INFO: Map<Record<string, string | number>, EnumInfo> = new Map();

function isValidEnumValue(val: string | number): boolean {
    return typeof val === 'number' && val >= 0;
}

export default class DataParser {

    static getDateTimeStr(value: Date): string | undefined {
        return value ? '' + value.getTime() : undefined;
    }

    static getEnumInfo(enumType: Record<string, string | number>): EnumInfo {
        let info = ENUM_TYPE_TO_INFO.get(enumType);
        if (info != null) {
            return info;
        }
        const nameToValue: Record<string | number, number> = {};
        const allEntries = Object.entries(enumType);
        const length = allEntries.length;
        for (let i = 0; i < length; i++) {
            const entry = allEntries[i];
            const value = entry[1] as number;
            if (!isValidEnumValue(value)) {
                continue;
            }
            nameToValue[entry[0]] = value;
            nameToValue[value] = value;
            nameToValue[value.toString()] = value;
        }
        info = {
            nameToValue,
            allNames: new Set(Object.keys(nameToValue)),
        };
        ENUM_TYPE_TO_INFO.set(enumType, info);
        return info;
    }

    static getEnumValue(value: any, enumType: Record<string, string | number>): number | undefined {
        this.getEnumInfo(enumType);
        if (value != null) {
            return ENUM_TYPE_TO_INFO.get(enumType)?.nameToValue[value];
        }
    }
}