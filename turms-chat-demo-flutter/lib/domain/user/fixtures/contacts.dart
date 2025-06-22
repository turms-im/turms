import 'package:fixnum/fixnum.dart';

import '../../../infra/random/random_utils.dart';
import '../../../ui/desktop/components/t_avatar/t_avatar.dart';
import '../../common/fixtures/fixtures.dart';
import '../models/group_member.dart';
import '../models/index.dart';

class _RawContactConversation {
  const _RawContactConversation(this.userName, this.messages);

  final String userName;

  final List<String> messages;
}

List<_RawContactConversation> _contactConversations = [
  // Earth
  const _RawContactConversation('Murmurs of Earth', [
    'Greetings in 55 languages',
    '𒁲𒈠𒃶𒈨𒂗',
    'Οἵτινές ποτ᾿ ἔστε χαίρετε! Εἰρηνικῶς πρὸς φίλους ἐληλύθαμεν φίλοι.',
    'Paz e felicidade a todos',
    '各位好嗎？祝各位平安健康快樂。',
    'Adanniš lu šulmu',
    'Здравствуйте! Приветствую Вас!',
    'สวัสดีค่ะ สหายในธรณีโพ้น พวกเราในธรณีนี้ขอส่งมิตรจิตมาถึงท่านทุกคน',
    '.تحياتنا للأصدقاء في النجوم. يا ليت يجمعنا الزمان',
    'Salutări la toată lumea',
    'Bonjour tout le monde',
    'နေကောင်းပါသလား',
    'שלום',
    'Hola y saludos a todos',
    'Selamat malam hadirin sekalian, selamat berpisah dan sampai bertemu lagi dilain waktu',
    'Kay pachamamta niytapas maytapas rimapallasta runasimipi',
    'ਆਓ ਜੀ, ਜੀ ਆਇਆਂ ਨੂੰ',
    'aššuli',
    'নমস্কার, বিশ্বে শান্তি হোক',
    'Salvete quicumque estis; bonam erga vos voluntatem habemus, et pacem per astra ferimus',
    '𐡌𐡋𐡔 or שלם or ܫܠܡ Šəlām',
    'Hartelijke groeten aan iedereen',
    'Herzliche Grüße an alle',
    'السلام عليکم ـ ہم زمين کے رہنے والوں کى طرف سے آپ کو خوش آمديد کہتے ھيں',
    'Chân thành gửi tới các bạn lời chào thân hữu',
    'Sayın Türkçe bilen arkadaşlarımız, sabah şerifleriniz hayrolsun',
    'こんにちは。お元気ですか？',
    'धरती के वासियों की ओर से नमस्कार',
    'Iechyd da i chi yn awr, ac yn oesoedd',
    'Tanti auguri e saluti',
    '	ආයුබෝවන්!',
    'Siya nibingelela maqhawe sinifisela inkonzo ende.',
    'Reani lumelisa marela.',
    '祝㑚大家好。',
    'Բոլոր անոնց որ կը գտնուին տիեզերգի միգամածութիւնէն անդին, ողջոյններ',
    '안녕하세요',
    'Witajcie, istoty z zaświatów.',
    'प्रिथ्वी वासीहरु बाट शान्ति मय भविष्य को शुभकामना',
    '各位都好吧？我们都很想念你们，有空请到这儿来玩。',
    'Mypone kaboutu noose.',
    'Hälsningar från en dataprogrammerare i den lilla universitetsstaden Ithaca på planeten Jorden',
    'Mulibwanji imwe boonse bantu bakumwamba.',
    'પૃથ્વી ઉપર વસનાર એક માનવ તરફથી બ્રહ્માંડના અન્ય અવકાશમાં વસનારાઓને હાર્દિક અભિનંદન. આ સંદેશો મળ્યે, વળતો સંદેશો મોકલાવશો.',
    "Пересилаємо привіт із нашого світу, бажаємо щастя, здоров'я і многая літа",
    'درود بر ساکنین ماورای آسمان‌ها',
    'Желимо вам све најлепше са наше планете',
    'ସୂର୍ଯ୍ୟ ତାରକାର ତୃତୀୟ ଗ୍ରହ ପୃଥିବୀରୁ ବିଶ୍ୱବ୍ରହ୍ମାଣ୍ଡର ଅଧିବାସୀ ମାନଙ୍କୁ ଅଭିନନ୍ଦନ',
    'Musulayo mutya abantu bensi eno mukama abawe emirembe bulijo.',
    'नमस्कार. ह्या पृथ्वीतील लोक तुम्हाला त्यांचे शुभविचार पाठवतात आणि त्यांची इच्छा आहे की तुम्ही ह्या जन्मी धन्य व्हा.',
    '太空朋友，恁好！恁食飽未？有閒著來阮遮坐喔。',
    'Üdvözletet küldünk magyar nyelven minden békét szerető lénynek a Világegyetemen',
    'నమస్తే, తెలుగు మాట్లాడే జనముననించి మా శుభాకాంక్షలు.',
    'Milí přátelé, přejeme vám vše nejlepší',
    'ನಮಸ್ತೆ, ಕನ್ನಡಿಗರ ಪರವಾಗಿ ಶುಭಾಷಯಗಳು.',
    '-',
    'Hello from the children of planet Earth',
    '![Solar System Portrait|4000x1200](https://voyager.jpl.nasa.gov/assets/images/galleries/images-voyager-took/solar-system-portrait/PIA00451.jpg)',
  ]),
  // China
  const _RawContactConversation('窦唯', [
    '暮春秋色',
    '多开阔',
    '幻声凋落',
    '曙分',
    '云舞',
    '冬穿梭',
    '往来经过',
    '手挥',
    '捕捉',
    '起风了',
    '骤雨夏天',
    '暮春',
    '秋色',
    '一清池',
    '姽婳妩媚',
    '万丘壑',
    '锦缎绫罗',
    '惑多',
    '已消落',
    '光阴归来',
    '变空白',
    '染尘埃',
    '一并敛埋',
  ]),
  // America
  const _RawContactConversation('Nina Simone', [
    '![Nina Simone - Stars / Feelings (Medley / Live at Montreux, 1976)](https://www.youtube.com/watch?v=Mf_5l1yTKNY)',
    '![butterfly|1280x720](https://flutter.github.io/assets-for-api-docs/assets/videos/butterfly.mp4)',
  ]),
  // Brazil
  const _RawContactConversation('Elis Regina', [
    'Como Nossos Pais',
    '![](https://flutter.github.io/assets-for-api-docs/assets/audio/rooster.mp3)',
  ]),
  // Cambodia
  const _RawContactConversation('រស់ សេរីសុទ្ធា', [
    'បើបងស្រឡាញ់ខ្ញុំ',
    'រស់ សេរីសុទ្ធា និង អឹម សឺុងសឹម',
    'រៀបរាងដោយ',
    '(ស្រី) បើ បងប្រាថ្នាចង់ស្រលាញ់ខ្ញុំ',
    'កុំ សើចកុំយំណា៎បងណា៎',
    'អូន ត្រូវសាកល្បង តាមត្រូវ ការ',
    'ឱ្យធ្វើយ៉ាងណា ធ្វើយ៉ាងណា',
    'កុំប្រកែកឡើយ។',
    'បើ បងប្រាថ្នាចង់ស្រលាញ់ខ្ញុំ',
    'កុំ សើចកុំយំ ត្រូវគិតឱ្យហើយ',
    'ក្រែង លោបងជូន',
    'អូនមិនបានដល់ត្រើយ',
    'តិចលោបកក្រោយ',
    'ចោលឱ្យអូន នៅកណ្ដាលផ្លូវ។',
    '(ប្រុសបន្ទរ) គ្មានបញ្ហា ចោទថា',
    'បងទ្រាំ មិនបាន ទេស្រី',
    'ចុះទឹក ក្រពើ ឡើងលើ ខ្លាត្រី',
    'ក៏បង ព្រមដែរ។',
    '(ស្រី) បើ បងប្រាថ្នាចង់ស្រលាញ់ខ្ញុំ',
    'កុំ សើចកុំយំ កុំត្អូញត្អែរ',
    'និ យាយដូចបង ក៏ល្មមព្រមដែរ',
    'គួររកគ្រូស្នេហ៍',
    'ដាក់ថ្នមថែរអស់មួយជីវិត។',
    '(ស្រី) បើ បងប្រាថ្នាចង់ស្រលាញ់ខ្ញុំ',
    'កុំ សើចកុំយំណា៎បងណា៎',
    'អូន ត្រូវសាកល្បង តាមត្រូវ ការ',
    'ឱ្យធ្វើយ៉ាងណា ធ្វើយ៉ាងណា',
    'កុំប្រកែកឡើយ។',
    'បើ បងប្រាថ្នាចង់ស្រលាញ់ខ្ញុំ',
    'កុំ សើចកុំយំ ត្រូវគិតឱ្យហើយ',
    'ក្រែង លោបងជូន',
    'អូនមិនបានដល់ត្រើយ',
    'តិចលោបកក្រោយ',
    'ចោលឱ្យអូន នៅកណ្ដាលផ្លូវ។',
    '(ប្រុសបន្ទរ) គ្មានបញ្ហា ចោទថា',
    'បងទ្រាំ មិនបាន ទេស្រី',
    'ចុះទឹក ក្រពើ ឡើងលើ ខ្លាត្រី',
    'ក៏បង ព្រមដែរ។',
    '(ស្រី) បើ បងប្រាថ្នាចង់ស្រលាញ់ខ្ញុំ',
    'កុំ សើចកុំយំ កុំត្អូញត្អែរ',
    'និ យាយដូចបង ក៏ល្មមព្រមដែរ',
    'គួររកគ្រូស្នេហ៍',
    'ដាក់ថ្នមថែរអស់មួយជីវិត។',
    'កុំ សើចកុំយំ កុំត្អូញត្អែរ',
    'កុំ សើចកុំយំ កុំត្អូញត្អែរ',
    'បើ បងប្រាថ្នាចង់ស្រលាញ់ខ្ញុំ',
    'បើ បងប្រាថ្នាចង់ស្រលាញ់ខ្ញុំ',
    'បើ បងប្រាថ្នាចង់ស្រលាញ់ខ្ញុំ',
    'បើ បងប្រាថ្នាចង់ស្រលាញ់ខ្ញុំ។',
    'ដោយក្តីស្រឡាញ់ពីខ្ញុំ ស៊ិន ស៊ីតារា',
  ]),
  // Italy
  const _RawContactConversation('Piero Piccioni', ['Amore Mio Aiutami']),
  // South Korea
  const _RawContactConversation('MC몽', [
    '![MC MONG MC몽 ‘죽을 만큼 아파서 (Feat. JAMIE (제이미))’ Live Performance](https://www.youtube.com/watch?v=FdAIE6Z4S9k)',
    '![404](https://a-wrong-url.com/404.png)',
  ]),
  // Japan
  const _RawContactConversation('Nujabes', ['Aruarian Dance']),
];

final _userContacts = _contactConversations.indexed.map((item) {
  final (index, contactConversation) = item;
  return UserContact(
    userId: Int64(index + 1),
    name: contactConversation.userName,
    relationshipGroupId: Int64(1),
    presence:
        UserPresence.values[1 + (index % (UserPresence.values.length - 1))],
  );
}).toList();

final userContactIdToMessages = <Int64, List<String>>{
  for (final userContact in _userContacts)
    userContact.userId: _contactConversations
        .firstWhere((item) => item.userName == userContact.name)
        .messages,
};

extension FixturesExtensions on Fixtures {
  List<UserContact> get userContacts => _userContacts;

  List<GroupContact> getFixtureGroupContacts(User loggedInUser) =>
      userContacts.indexed.map((item) {
        final (index, _) = item;
        final members = <GroupMember>[];
        final memberCount = index + 1;
        for (var i = 0; i < memberCount; i++) {
          members.add(GroupMember.fromUser(userContacts[i], isAdmin: i == 0));
        }
        members.add(
          GroupMember.fromUser(loggedInUser, isAdmin: RandomUtils.nextBool()),
        );
        return GroupContact(
          groupId: Int64(index + 1),
          members: members,
          name: 'fake group name.' * 10,
        );
      }).toList();

  List<Contact> getContacts(User loggedInUser) => [
    ..._userContacts,
    ...getFixtureGroupContacts(loggedInUser),
  ];
}
