# Content Moderation

Turms does not support and will not support the anti-spam detection function of pictures, videos and voices in the future. All the content below is only explained within the scope of text detection.

## Feature Comparison

Combined with the actual situation, the biggest advantage of the commercial sensitive word filtering function is: rich thesaurus, timely update, and support for multiple languages. The main disadvantages are: the fee is charged according to the number of detections, and a network request needs to be sent for each detection; the biggest advantage of turms-plugin-antispam is: free, local fast detection, only need to traverse the target string once. The main disadvantage is: no thesaurus is provided. in particular:

In particular, due to the objective existence of black products, the actual cost of "charging by the number of tests" may be greater than your expected cost.

| | Commercial antispam service (including sensitive word filtering) | turms-plugin-antispam |
| --------------------------------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| Free | No. Billed per test | Yes |
| Open Source | No. Fully closed source | Yes. Fully open source |
| Matching speed | Need to send a network request, which is several orders of magnitude slower than the matching speed of turms-plugin-antispam | Local extremely fast matching (implemented by AC automaton algorithm based on double array Trie). You can ignore the performance overhead of matching. <br />In NORMALIZATION mode, the time complexity of matching is O(n), where n is the length of the input string. <br />In NORMALIZATION_TRANSLITERATION mode, the time complexity of transliteration is O(n), where n is the length of the input string. The time complexity of matching the transliteration result is O(m), where m is the string length of the transliteration result. <br />Supplement: Transliteration of Chinese characters refers to the conversion of Chinese characters into pinyin |
| Text denoising (e.g. depunctuation, letter and number normalization) | Partially supported | Partially supported |
| Shape and word matching (such as Martian) | Partial support | TODO (1.1 support) |
| Split word matching | Partial support | TODO (1.2 support) |
| Accurate matching of sounds and words | Support | Support |
| Fuzzy matching of sounds and words | Support | TODO (1.1 support) |
| polyphone matching | support | TODO (1.1 support) |
| Thesaurus | Closed source, but thesaurus is rich and updated in a timely manner | Not provided. See below for specific reasons |
| Multi-language/dialect support | Support for multiple languages and dialects | Users need to collect thesaurus by themselves. In addition, there are also projects that translate the source language into a specific language and then match it by calling the "translation API", but turms-plugin-antispam does not provide this kind of implementation |
| Rare word support | Partial support | Partial support. turms-plugin-antispam can recognize code points in the Unicode Basic Multilingual Plane (BMP), and supports the recognition of more than 20,000 Chinese characters (the latest version of "Xinhua Dictionary" only includes more than 10,000 Chinese characters). <br />Because most IM applications do not require to be able to display particularly uncommon characters (such as "𤳵"), it is recommended that your UI front-end application directly use placeholders such as "?" points to replace. <br />turms-plugin-antispam has no plans to support code points other than BMP |
| Combining sensitive words | Support | TODO (1.1 support) |
| Vertical Text Detection | Not Supported | Not Supported |
| Additional information on query thesaurus | Abundant additional information. Such as sensitive word categories (pornography, politics, terrorism, prohibition, abuse, flooding, advertising, advertising law, values, etc.) | TODO (1.0). In addition, although Turms will support this function in the future, Turms still does not provide sensitive thesaurus |
| Whitelist | Support | TODO (1.1 support) |
| Regionally differentiated services | Partially supported | Not supported |
| Manual review system | Partially supported | Not supported |

## Complexity of sensitive word detection
* Not all text can be detected. Take the string "Turms is an excellent IM open source project" as an example, if we use conventional vertical plaintext display. Then if the sensitive word detection system does not support feature extraction, then the system cannot detect this type of text:

     ```text
     ╔═╤═╤═╤═╤═╗
     ║┊│item│of│is│T║
     ║┊│目│I│一│u║
     ║┊│┊│M│a│r║
     ║┊│┊│Open│Excellent│｜║
     ║┊│┊│source│show│s║
     ╚═╧═╧═╧═╧═╝
     ```

  You can even use an encryption algorithm to encrypt the message before sending it (for example, if your application supports the web side, the hacker can even write a browser plug-in for your web side, so that each message is encrypted before sending, decrypted upon receipt). Therefore, sensitive word filtering can only increase the cost of sending sensitive words, but cannot eradicate the behavior of sending sensitive words.

* Most of the systems do not support detection semantics, especially the positive and the negative. The same sentence, placed in different contexts, can have completely different meanings.

* There is a wrong seal. Some normal words have been wrongly blocked. For example, many commercial services regard "water and milk" as "a highly credible pornographic word", which results in the loss of normal users. Supplement: In order to avoid this kind of mis-blocking, these words need to be entered in the "white list" (turms-plugin-antispam is not currently available).

* The implementation can be very flexible (turms-plugin-antispam has no plans to provide related implementations). Specifically, it includes: providing different sensitive word filtering services according to users in different regions; compared with private chat messages, stricter detection of group messages; some words are time-sensitive, sometimes sensitive words, and sometimes Time is not a sensitive word.

In view of the complexity of the above-mentioned sensitive word detection, what `turms-plugin-antispam` needs to do is: combine the marginal effects, comprehensively consider the breadth of sensitive words, the difficulty of recognizing sensitive words, and the system resource overhead required for recognition. It is only required to be able to identify most sensitive words and common camouflage methods, and it is not required to be able to identify relatively rare camouflage forms.

## Thesaurus of sensitive words

Due to the particularity of sensitive words, turms-plugin-antispam does not provide a thesaurus of sensitive words, which needs to be collected by users themselves.

### source

Network collection, operation collection, reverse engineering, provision by relevant departments, manual reporting

### role

* Provide sensitive word thesaurus for turms-plugin-antispam for subsequent sensitive word detection. Except for the `word` field, other fields currently **temporarily** have no practical effect on sensitive word detection

* (TODO) Provide Admin API interface for operators to query and fully update the sensitive word thesaurus

  In the short term, we have no plan to support the import of raw data from the database, as well as the operational functions of the sensitive word thesaurus. You need to manage the sensitive word thesaurus yourself, turms-plugin-antispam is currently only used as a pure data user

### Format

turms-plugin-antispam currently supports two formats for importing thesaurus: 1. Original CSV format data; 2. Parsed bin data.

Because the parsed bin data is imported, turms-plugin-antispam does not need to analyze the thesaurus, but simply reads the byte stream data of the file, so it is strongly recommended that you use `im.turms.plugin.antispam.ac .AhoCorasickCodec#main` parses the original CSV format data into bin data by itself, and then lets turms-plugin-antispam directly import bin data through `dictParsing.binFilePath` (TODO: will soon support data import through Admin API)

#### CSV format

```spreadsheet
Sensitive word, word ID, filter level, category, etymology, collection time, expiration time, effective time, update time, comment
word, id, level, category, source, create_time, disable_time, enable_time, update_time, comment
```

* ** Pay special attention to the imported file format, it is highly recommended to use `UTF-8` encoding**
* Both `,` and `\t` will be automatically recognized, no need for users to specify manually
* Among all the above columns, except the `word` column must exist, all other columns may not exist
* If it is parsing to a data format that does not match the column format, it will refuse to continue parsing and throw an exception

Considering that the above columns can already meet most scenarios, it is not planned to support the `custom column` feature.

For example:

```spreadsheet
Hello, 123,1, Greeting words for greeting, network collection, 1970-01-01T00:00:00.000Z, 1970-01-01T00:00:00.000Z, 1970-01-01T00:00:00.000Z, Chinese Greeting common words in Chinese
Hello,,2,,

안녕하세요,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
こんにちは
```

## Configuration explanation

Configuration class: `im.turms.plugin.antispam.property.AntiSpamProperties`

Configuration prefix: `turms.plugin.antispam`

### configuration items

| configuration name | default value | role |
| ------------------------------------ | ----------------------------- | ------------------------------------------------------------ |
| enabled | true | Whether to enable the anti-spam function |
| dictParsing.binFilePath | null | The binary file path of the dictionary. This file saves the parsed data of the thesaurus text, which is used to avoid parsing the thesaurus text from the beginning each time the server starts. If the user configures "textFilePath" and "binFilePath", "binFilePath" will be used first |
| dictParsing.textFilePath | null | Text file path of the thesaurus |
| dictParsing.textFileCharset | "UTF-8" | Thesaurus encoding format. It is recommended to use "UTF-8" encoding uniformly |
| dictParsing.skipInvalidCharacter | true | Whether to automatically skip invalid characters when parsing the thesaurus text. <br />If false and an illegal character is encountered during parsing, an exception will be thrown |
| dictParsing.extendedWords.enabled | true | Whether to support the extended word library function. If `true`, all data in the thesaurus is parsed and used. If it is `false`, only parse and use `word` field data to greatly reduce memory overhead |
| textParsingStrategy | NORMALIZATION_TRANSLITERATION | Parsing strategy for dictionary text and user input text:<br />NORMALIZATION: Normalize the input text. For example: `⑩HELLO(你{}好./` -> `10hello`<br />NORMALIZATION_TRANSLITERATION: Standardize and transliterate the input text. For example: `⑩HELLO(你{}好./` -> `10hellonihao` |
| unwantedWordHandleStrategy | REJECT_REQUEST | Illegal text processing strategy:<br />REJECT_REQUEST: return "MESSAGE_IS_ILLEGAL" error status code to the client<br />MASK_TEXT: replace illegal characters, and continue to process the request normally |
| mask | '*' | When "unwantedWordHandleStrategy" is "MASK_TEXT", the mask used |
| maxNumberOfUnwantedWordsToReturn | 0 | When the processing strategy is `REJECT_REQUEST` and the value is greater than 0, the character string detected as illegal text will use ASCII `0x1E` (Record Separator) express. The exception text will eventually be received by the client |
| textTypes | All other user-visible text | Configure which text fields of which requests should be checked |
| silentIllegalTextTypes | Empty | Configure When detecting that these text fields of these requests contain illegal characters, the server will respond to the client with an "OK" status code, but the server does not actually continue processing the request. <br />In actual business scenarios, this value is usually `CREATE_MESSAGE_REQUEST_TEXT`, which is used to silently refuse to send user messages |

## Admin API

TODO

## Reasons not to use other open source implementations

In the global open source circle, the quality of open source implementations currently available is very low, mainly reflected in: low code quality (high space complexity and time complexity), many matching functions are not supported, and the author does not have engineering design capabilities , There are even paid semi-open source IM projects that perform matching by traversing the thesaurus. There is no implementation of an algorithm and code quality like turms-plugin-antispam, and the overall implementation of traditional anti-spam solutions (not involving machine learning) is not difficult, so Turms chooses self-developed, and also contributes to many later expansions. fully prepared. in particular:

* Those who know algorithms do not know engineering design, and those who know engineering design do not know how to do algorithms. On the one hand, it is difficult to implement the AC automaton algorithm based on the double array Trie, and the data structure design of Java is relatively conservative, such as `String` and `StringBuilder`. In order to ensure the isolation of internal data and external data, many functions will involve For memory copy work, it is necessary for engineers to have basic optimization awareness to avoid various Java "pitfalls" in algorithm implementation. On the other hand, the logic of the anti-spam design and algorithm implementation in Turms is unified, and they are all designed for the IM project of Turms and serve the actual IM needs. Therefore, it can be guaranteed that "the functions that can be imagined can be realized, and the unnecessary functions do not need to be provided, so as to avoid unnecessary time and space overhead".
* Self-research can customize the algorithm implementation and the upstream and downstream codes of the algorithm according to the project requirements to ensure absolute efficiency (press the space complexity to O(1), time complexity to O(n), and ensure that one side of the string is traversed Sensitive word matching can be completed). For example, in the implementation of the AC automaton standard algorithm, the logic of "skipping a certain character for matching" is not involved. Then if we want to achieve "only detect code points in BMP", we need to filter and copy a new char[] before passing the original char[] to the standard AC algorithm, and then pass it to the AC automaton to match. This frequent memory copy work is undoubtedly very inefficient and unnecessary, especially the "user text message" itself is the most memory-intensive and frequently occurring data among all user requests. However, if a custom implementation is used, we only need to add an if judgment condition to skip the character directly when the AC automaton performs matching. It is simple and clear, and does not need to open up a new memory space, so the space efficiency is high.