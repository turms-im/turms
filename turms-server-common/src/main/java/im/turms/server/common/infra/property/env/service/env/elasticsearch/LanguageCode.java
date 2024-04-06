/*
 * Copyright (C) 2019 The Turms Project
 * https://github.com/turms-im/turms
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package im.turms.server.common.infra.property.env.service.env.elasticsearch;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author James Chen
 */
@AllArgsConstructor
public enum LanguageCode {
    NONE("", "", ""),

    AF("af", "af", "Afrikaans"),
    AM("am", "am", "Amharic"),
    AR("ar", "ar", "Arabic"),
    AZ("az", "az", "Azerbaijani"),
    BE("be", "be", "Belarusian"),
    BG("bg", "bg", "Bulgarian"),
    BG_LATN("bg-Latn", "bg_latn", "Bulgarian"),
    BN("bn", "bn", "Bengali"),
    BS("bs", "bs", "Bosnian"),
    CA("ca", "ca", "Catalan"),
    CEB("ceb", "ceb", "Cebuano"),
    CO("co", "co", "Corsican"),
    CS("cs", "cs", "Czech"),
    CY("cy", "cy", "Welsh"),
    DA("da", "da", "Danish"),
    DE("de", "de", "German"),
    EL("el", "el", "Greek, modern"),
    EL_LATN("el-Latn", "el_latn", "Greek, modern"),
    EN("en", "en", "English"),
    EO("eo", "eo", "Esperanto"),
    ES("es", "es", "Spanish, Castilian"),
    ET("et", "et", "Estonian"),
    EU("eu", "eu", "Basque"),
    FA("fa", "fa", "Persian"),
    FI("fi", "fi", "Finnish"),
    FIL("fil", "fil", "Filipino"),
    FR("fr", "fr", "French"),
    FY("fy", "fy", "Western Frisian"),
    GA("ga", "ga", "Irish"),
    GD("gd", "gd", "Gaelic"),
    GL("gl", "gl", "Galician"),
    GU("gu", "gu", "Gujarati"),
    HA("ha", "ha", "Hausa"),
    HAW("haw", "haw", "Hawaiian"),
    HI("hi", "hi", "Hindi"),
    HI_LATN("hi-Latn", "hi_latn", "Hindi"),
    HMN("hmn", "hmn", "Hmong"),
    HR("hr", "hr", "Croatian"),
    HT("ht", "ht", "Haitian"),
    HU("hu", "hu", "Hungarian"),
    HY("hy", "hy", "Armenian"),
    ID("id", "id", "Indonesian"),
    IG("ig", "ig", "Igbo"),
    IS("is", "is", "Icelandic"),
    IT("it", "it", "Italian"),
    IW("iw", "iw", "Hebrew"),
    JA("ja", "ja", "Japanese"),
    JA_LATN("ja-Latn", "ja_latn", "Japanese"),
    JV("jv", "jv", "Javanese"),
    KA("ka", "ka", "Georgian"),
    KK("kk", "kk", "Kazakh"),
    KM("km", "km", "Central Khmer"),
    KN("kn", "kn", "Kannada"),
    KO("ko", "ko", "Korean"),
    KU("ku", "ku", "Kurdish"),
    KY("ky", "ky", "Kirghiz"),
    LA("la", "la", "Latin"),
    LB("lb", "lb", "Luxembourgish"),
    LO("lo", "lo", "Lao"),
    LT("lt", "lt", "Lithuanian"),
    LV("lv", "lv", "Latvian"),
    MG("mg", "mg", "Malagasy"),
    MI("mi", "mi", "Maori"),
    MK("mk", "mk", "Macedonian"),
    ML("ml", "ml", "Malayalam"),
    MN("mn", "mn", "Mongolian"),
    MR("mr", "mr", "Marathi"),
    MS("ms", "ms", "Malay"),
    MT("mt", "mt", "Maltese"),
    MY("my", "my", "Burmese"),
    NE("ne", "ne", "Nepali"),
    NL("nl", "nl", "Dutch, Flemish"),
    NO("no", "no", "Norwegian"),
    NY("ny", "ny", "Chichewa"),
    PA("pa", "pa", "Punjabi"),
    PL("pl", "pl", "Polish"),
    PS("ps", "ps", "Pashto"),
    PT("pt", "pt", "Portuguese"),
    RO("ro", "ro", "Romanian"),
    RU("ru", "ru", "Russian"),
    RU_LATN("ru-Latn", "ru_latn", "Russian"),
    SD("sd", "sd", "Sindhi"),
    SI("si", "si", "Sinhala"),
    SK("sk", "sk", "Slovak"),
    SL("sl", "sl", "Slovenian"),
    SM("sm", "sm", "Samoan"),
    SN("sn", "sn", "Shona"),
    SO("so", "so", "Somali"),
    SQ("sq", "sq", "Albanian"),
    SR("sr", "sr", "Serbian"),
    ST("st", "st", "Southern Sotho"),
    SU("su", "su", "Sundanese"),
    SV("sv", "sv", "Swedish"),
    SW("sw", "sw", "Swahili"),
    TA("ta", "ta", "Tamil"),
    TE("te", "te", "Telugu"),
    TG("tg", "tg", "Tajik"),
    TH("th", "th", "Thai"),
    TR("tr", "tr", "Turkish"),
    UK("uk", "uk", "Ukrainian"),
    UR("ur", "ur", "Urdu"),
    UZ("uz", "uz", "Uzbek"),
    VI("vi", "vi", "Vietnamese"),
    XH("xh", "xh", "Xhosa"),
    YI("yi", "yi", "Yiddish"),
    YO("yo", "yo", "Yoruba"),
    ZH("zh", "zh", "Chinese"),
    ZH_LATN("zh-Latn", "zh_latn", "Chinese"),
    ZU("zu", "zu", "Zulu");

    @Getter
    private final String code;

    @Getter
    private final String canonicalCode;

    @Getter
    private final String language;

}