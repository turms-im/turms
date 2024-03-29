// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: model/user/user_location.proto

#include "turms/client/model/proto/model/user/user_location.pb.h"

#include <algorithm>
#include "google/protobuf/io/coded_stream.h"
#include "google/protobuf/extension_set.h"
#include "google/protobuf/wire_format_lite.h"
#include "google/protobuf/io/zero_copy_stream_impl_lite.h"
#include "google/protobuf/generated_message_tctable_impl.h"
// @@protoc_insertion_point(includes)

// Must be included last.
#include "google/protobuf/port_def.inc"
PROTOBUF_PRAGMA_INIT_SEG
namespace _pb = ::google::protobuf;
namespace _pbi = ::google::protobuf::internal;
namespace _fl = ::google::protobuf::internal::field_layout;
namespace turms {
namespace client {
namespace model {
namespace proto {
      template <typename>
PROTOBUF_CONSTEXPR UserLocation_DetailsEntry_DoNotUse::UserLocation_DetailsEntry_DoNotUse(::_pbi::ConstantInitialized) {}
struct UserLocation_DetailsEntry_DoNotUseDefaultTypeInternal {
  PROTOBUF_CONSTEXPR UserLocation_DetailsEntry_DoNotUseDefaultTypeInternal() : _instance(::_pbi::ConstantInitialized{}) {}
  ~UserLocation_DetailsEntry_DoNotUseDefaultTypeInternal() {}
  union {
    UserLocation_DetailsEntry_DoNotUse _instance;
  };
};

PROTOBUF_ATTRIBUTE_NO_DESTROY PROTOBUF_CONSTINIT
    PROTOBUF_ATTRIBUTE_INIT_PRIORITY1 UserLocation_DetailsEntry_DoNotUseDefaultTypeInternal _UserLocation_DetailsEntry_DoNotUse_default_instance_;
        template <typename>
PROTOBUF_CONSTEXPR UserLocation::UserLocation(::_pbi::ConstantInitialized)
    : _impl_{
      /*decltype(_impl_._has_bits_)*/ {},
      /*decltype(_impl_._cached_size_)*/ {},
      /* decltype(_impl_.details_) */ {},
      /*decltype(_impl_.latitude_)*/ 0,
      /*decltype(_impl_.longitude_)*/ 0,
      /*decltype(_impl_.timestamp_)*/ ::int64_t{0},
    } {}
struct UserLocationDefaultTypeInternal {
  PROTOBUF_CONSTEXPR UserLocationDefaultTypeInternal() : _instance(::_pbi::ConstantInitialized{}) {}
  ~UserLocationDefaultTypeInternal() {}
  union {
    UserLocation _instance;
  };
};

PROTOBUF_ATTRIBUTE_NO_DESTROY PROTOBUF_CONSTINIT
    PROTOBUF_ATTRIBUTE_INIT_PRIORITY1 UserLocationDefaultTypeInternal _UserLocation_default_instance_;
}  // namespace proto
}  // namespace model
}  // namespace client
}  // namespace turms
namespace turms {
namespace client {
namespace model {
namespace proto {
// ===================================================================

UserLocation_DetailsEntry_DoNotUse::UserLocation_DetailsEntry_DoNotUse() {}
UserLocation_DetailsEntry_DoNotUse::UserLocation_DetailsEntry_DoNotUse(::google::protobuf::Arena* arena)
    : SuperType(arena) {}
void UserLocation_DetailsEntry_DoNotUse::MergeFrom(const UserLocation_DetailsEntry_DoNotUse& other) {
  MergeFromInternal(other);
}
// ===================================================================

class UserLocation::_Internal {
 public:
  using HasBits = decltype(std::declval<UserLocation>()._impl_._has_bits_);
  static constexpr ::int32_t kHasBitsOffset =
    8 * PROTOBUF_FIELD_OFFSET(UserLocation, _impl_._has_bits_);
  static void set_has_timestamp(HasBits* has_bits) {
    (*has_bits)[0] |= 1u;
  }
};

UserLocation::UserLocation(::google::protobuf::Arena* arena)
    : ::google::protobuf::MessageLite(arena) {
  SharedCtor(arena);
  // @@protoc_insertion_point(arena_constructor:turms.client.model.proto.UserLocation)
}
UserLocation::UserLocation(const UserLocation& from) : ::google::protobuf::MessageLite() {
  UserLocation* const _this = this;
  (void)_this;
  new (&_impl_) Impl_{
      decltype(_impl_._has_bits_){from._impl_._has_bits_},
      /*decltype(_impl_._cached_size_)*/ {},
      /* decltype(_impl_.details_) */ {},
      decltype(_impl_.latitude_){},
      decltype(_impl_.longitude_){},
      decltype(_impl_.timestamp_){},
  };
  _internal_metadata_.MergeFrom<std::string>(
      from._internal_metadata_);
  _this->_impl_.details_.MergeFrom(from._impl_.details_);
  ::memcpy(&_impl_.latitude_, &from._impl_.latitude_,
    static_cast<::size_t>(reinterpret_cast<char*>(&_impl_.timestamp_) -
    reinterpret_cast<char*>(&_impl_.latitude_)) + sizeof(_impl_.timestamp_));

  // @@protoc_insertion_point(copy_constructor:turms.client.model.proto.UserLocation)
}
inline void UserLocation::SharedCtor(::_pb::Arena* arena) {
  (void)arena;
  new (&_impl_) Impl_{
      decltype(_impl_._has_bits_){},
      /*decltype(_impl_._cached_size_)*/ {},
      /* decltype(_impl_.details_) */ {::google::protobuf::internal::ArenaInitialized(), arena},
      decltype(_impl_.latitude_){0},
      decltype(_impl_.longitude_){0},
      decltype(_impl_.timestamp_){::int64_t{0}},
  };
}
UserLocation::~UserLocation() {
  // @@protoc_insertion_point(destructor:turms.client.model.proto.UserLocation)
  _internal_metadata_.Delete<std::string>();
  SharedDtor();
}
inline void UserLocation::SharedDtor() {
  ABSL_DCHECK(GetArenaForAllocation() == nullptr);
  _impl_.details_.~MapFieldLite();
}
void UserLocation::SetCachedSize(int size) const {
  _impl_._cached_size_.Set(size);
}

PROTOBUF_NOINLINE void UserLocation::Clear() {
// @@protoc_insertion_point(message_clear_start:turms.client.model.proto.UserLocation)
  ::uint32_t cached_has_bits = 0;
  // Prevent compiler warnings about cached_has_bits being unused
  (void) cached_has_bits;

  _impl_.details_.Clear();
  ::memset(&_impl_.latitude_, 0, static_cast<::size_t>(
      reinterpret_cast<char*>(&_impl_.longitude_) -
      reinterpret_cast<char*>(&_impl_.latitude_)) + sizeof(_impl_.longitude_));
  _impl_.timestamp_ = ::int64_t{0};
  _impl_._has_bits_.Clear();
  _internal_metadata_.Clear<std::string>();
}

const char* UserLocation::_InternalParse(
    const char* ptr, ::_pbi::ParseContext* ctx) {
  ptr = ::_pbi::TcParser::ParseLoop(this, ptr, ctx, &_table_.header);
  return ptr;
}


PROTOBUF_CONSTINIT PROTOBUF_ATTRIBUTE_INIT_PRIORITY1
const ::_pbi::TcParseTable<2, 4, 1, 53, 2> UserLocation::_table_ = {
  {
    PROTOBUF_FIELD_OFFSET(UserLocation, _impl_._has_bits_),
    0, // no _extensions_
    4, 24,  // max_field_number, fast_idx_mask
    offsetof(decltype(_table_), field_lookup_table),
    4294967280,  // skipmap
    offsetof(decltype(_table_), field_entries),
    4,  // num_field_entries
    1,  // num_aux_entries
    offsetof(decltype(_table_), aux_entries),
    &_UserLocation_default_instance_._instance,
    ::_pbi::TcParser::GenericFallbackLite,  // fallback
  }, {{
    {::_pbi::TcParser::MiniParse, {}},
    // float latitude = 1;
    {::_pbi::TcParser::FastF32S1,
     {13, 63, 0, PROTOBUF_FIELD_OFFSET(UserLocation, _impl_.latitude_)}},
    // float longitude = 2;
    {::_pbi::TcParser::FastF32S1,
     {21, 63, 0, PROTOBUF_FIELD_OFFSET(UserLocation, _impl_.longitude_)}},
    // optional int64 timestamp = 3;
    {::_pbi::TcParser::FastV64S1,
     {24, 0, 0, PROTOBUF_FIELD_OFFSET(UserLocation, _impl_.timestamp_)}},
  }}, {{
    65535, 65535
  }}, {{
    // float latitude = 1;
    {PROTOBUF_FIELD_OFFSET(UserLocation, _impl_.latitude_), -1, 0,
    (0 | ::_fl::kFcSingular | ::_fl::kFloat)},
    // float longitude = 2;
    {PROTOBUF_FIELD_OFFSET(UserLocation, _impl_.longitude_), -1, 0,
    (0 | ::_fl::kFcSingular | ::_fl::kFloat)},
    // optional int64 timestamp = 3;
    {PROTOBUF_FIELD_OFFSET(UserLocation, _impl_.timestamp_), _Internal::kHasBitsOffset + 0, 0,
    (0 | ::_fl::kFcOptional | ::_fl::kInt64)},
    // map<string, string> details = 4;
    {PROTOBUF_FIELD_OFFSET(UserLocation, _impl_.details_), -1, 0,
    (0 | ::_fl::kFcRepeated | ::_fl::kMap)},
  }}, {{
    {::_pbi::TcParser::GetMapAuxInfo<decltype(UserLocation()._impl_.details_)>(1, 0, 0)},
  }}, {{
    "\45\0\0\0\7\0\0\0"
    "turms.client.model.proto.UserLocation"
    "details"
  }},
};

::uint8_t* UserLocation::_InternalSerialize(
    ::uint8_t* target,
    ::google::protobuf::io::EpsCopyOutputStream* stream) const {
  // @@protoc_insertion_point(serialize_to_array_start:turms.client.model.proto.UserLocation)
  ::uint32_t cached_has_bits = 0;
  (void)cached_has_bits;

  // float latitude = 1;
  static_assert(sizeof(::uint32_t) == sizeof(float),
                "Code assumes ::uint32_t and float are the same size.");
  float tmp_latitude = this->_internal_latitude();
  ::uint32_t raw_latitude;
  memcpy(&raw_latitude, &tmp_latitude, sizeof(tmp_latitude));
  if (raw_latitude != 0) {
    target = stream->EnsureSpace(target);
    target = ::_pbi::WireFormatLite::WriteFloatToArray(
        1, this->_internal_latitude(), target);
  }

  // float longitude = 2;
  static_assert(sizeof(::uint32_t) == sizeof(float),
                "Code assumes ::uint32_t and float are the same size.");
  float tmp_longitude = this->_internal_longitude();
  ::uint32_t raw_longitude;
  memcpy(&raw_longitude, &tmp_longitude, sizeof(tmp_longitude));
  if (raw_longitude != 0) {
    target = stream->EnsureSpace(target);
    target = ::_pbi::WireFormatLite::WriteFloatToArray(
        2, this->_internal_longitude(), target);
  }

  cached_has_bits = _impl_._has_bits_[0];
  // optional int64 timestamp = 3;
  if (cached_has_bits & 0x00000001u) {
    target = ::google::protobuf::internal::WireFormatLite::
        WriteInt64ToArrayWithField<3>(
            stream, this->_internal_timestamp(), target);
  }

  // map<string, string> details = 4;
  if (!_internal_details().empty()) {
    using MapType = ::google::protobuf::Map<std::string, std::string>;
    using WireHelper = UserLocation_DetailsEntry_DoNotUse::Funcs;
    const auto& field = _internal_details();

    if (stream->IsSerializationDeterministic() && field.size() > 1) {
      for (const auto& entry : ::google::protobuf::internal::MapSorterPtr<MapType>(field)) {
        target = WireHelper::InternalSerialize(
            4, entry.first, entry.second, target, stream);
        ::google::protobuf::internal::WireFormatLite::VerifyUtf8String(
            entry.first.data(), static_cast<int>(entry.first.length()),
 ::google::protobuf::internal::WireFormatLite::SERIALIZE, "turms.client.model.proto.UserLocation.details");
        ::google::protobuf::internal::WireFormatLite::VerifyUtf8String(
            entry.second.data(), static_cast<int>(entry.second.length()),
 ::google::protobuf::internal::WireFormatLite::SERIALIZE, "turms.client.model.proto.UserLocation.details");
      }
    } else {
      for (const auto& entry : field) {
        target = WireHelper::InternalSerialize(
            4, entry.first, entry.second, target, stream);
        ::google::protobuf::internal::WireFormatLite::VerifyUtf8String(
            entry.first.data(), static_cast<int>(entry.first.length()),
 ::google::protobuf::internal::WireFormatLite::SERIALIZE, "turms.client.model.proto.UserLocation.details");
        ::google::protobuf::internal::WireFormatLite::VerifyUtf8String(
            entry.second.data(), static_cast<int>(entry.second.length()),
 ::google::protobuf::internal::WireFormatLite::SERIALIZE, "turms.client.model.proto.UserLocation.details");
      }
    }
  }

  if (PROTOBUF_PREDICT_FALSE(_internal_metadata_.have_unknown_fields())) {
    target = stream->WriteRaw(
        _internal_metadata_.unknown_fields<std::string>(::google::protobuf::internal::GetEmptyString).data(),
        static_cast<int>(_internal_metadata_.unknown_fields<std::string>(::google::protobuf::internal::GetEmptyString).size()), target);
  }
  // @@protoc_insertion_point(serialize_to_array_end:turms.client.model.proto.UserLocation)
  return target;
}

::size_t UserLocation::ByteSizeLong() const {
// @@protoc_insertion_point(message_byte_size_start:turms.client.model.proto.UserLocation)
  ::size_t total_size = 0;

  ::uint32_t cached_has_bits = 0;
  // Prevent compiler warnings about cached_has_bits being unused
  (void) cached_has_bits;

  // map<string, string> details = 4;
  total_size += 1 * ::google::protobuf::internal::FromIntSize(_internal_details_size());
  for (const auto& entry : _internal_details()) {
    total_size += UserLocation_DetailsEntry_DoNotUse::Funcs::ByteSizeLong(entry.first, entry.second);
  }
  // float latitude = 1;
  static_assert(sizeof(::uint32_t) == sizeof(float),
                "Code assumes ::uint32_t and float are the same size.");
  float tmp_latitude = this->_internal_latitude();
  ::uint32_t raw_latitude;
  memcpy(&raw_latitude, &tmp_latitude, sizeof(tmp_latitude));
  if (raw_latitude != 0) {
    total_size += 5;
  }

  // float longitude = 2;
  static_assert(sizeof(::uint32_t) == sizeof(float),
                "Code assumes ::uint32_t and float are the same size.");
  float tmp_longitude = this->_internal_longitude();
  ::uint32_t raw_longitude;
  memcpy(&raw_longitude, &tmp_longitude, sizeof(tmp_longitude));
  if (raw_longitude != 0) {
    total_size += 5;
  }

  // optional int64 timestamp = 3;
  cached_has_bits = _impl_._has_bits_[0];
  if (cached_has_bits & 0x00000001u) {
    total_size += ::_pbi::WireFormatLite::Int64SizePlusOne(
        this->_internal_timestamp());
  }

  if (PROTOBUF_PREDICT_FALSE(_internal_metadata_.have_unknown_fields())) {
    total_size += _internal_metadata_.unknown_fields<std::string>(::google::protobuf::internal::GetEmptyString).size();
  }
  int cached_size = ::_pbi::ToCachedSize(total_size);
  SetCachedSize(cached_size);
  return total_size;
}

void UserLocation::CheckTypeAndMergeFrom(
    const ::google::protobuf::MessageLite& from) {
  MergeFrom(*::_pbi::DownCast<const UserLocation*>(
      &from));
}

void UserLocation::MergeFrom(const UserLocation& from) {
  UserLocation* const _this = this;
  // @@protoc_insertion_point(class_specific_merge_from_start:turms.client.model.proto.UserLocation)
  ABSL_DCHECK_NE(&from, _this);
  ::uint32_t cached_has_bits = 0;
  (void) cached_has_bits;

  _this->_impl_.details_.MergeFrom(from._impl_.details_);
  static_assert(sizeof(::uint32_t) == sizeof(float),
                "Code assumes ::uint32_t and float are the same size.");
  float tmp_latitude = from._internal_latitude();
  ::uint32_t raw_latitude;
  memcpy(&raw_latitude, &tmp_latitude, sizeof(tmp_latitude));
  if (raw_latitude != 0) {
    _this->_internal_set_latitude(from._internal_latitude());
  }
  static_assert(sizeof(::uint32_t) == sizeof(float),
                "Code assumes ::uint32_t and float are the same size.");
  float tmp_longitude = from._internal_longitude();
  ::uint32_t raw_longitude;
  memcpy(&raw_longitude, &tmp_longitude, sizeof(tmp_longitude));
  if (raw_longitude != 0) {
    _this->_internal_set_longitude(from._internal_longitude());
  }
  if ((from._impl_._has_bits_[0] & 0x00000001u) != 0) {
    _this->_internal_set_timestamp(from._internal_timestamp());
  }
  _this->_internal_metadata_.MergeFrom<std::string>(from._internal_metadata_);
}

void UserLocation::CopyFrom(const UserLocation& from) {
// @@protoc_insertion_point(class_specific_copy_from_start:turms.client.model.proto.UserLocation)
  if (&from == this) return;
  Clear();
  MergeFrom(from);
}

PROTOBUF_NOINLINE bool UserLocation::IsInitialized() const {
  return true;
}

void UserLocation::InternalSwap(UserLocation* other) {
  using std::swap;
  _internal_metadata_.InternalSwap(&other->_internal_metadata_);
  swap(_impl_._has_bits_[0], other->_impl_._has_bits_[0]);
  _impl_.details_.InternalSwap(&other->_impl_.details_);
  ::google::protobuf::internal::memswap<
      PROTOBUF_FIELD_OFFSET(UserLocation, _impl_.timestamp_)
      + sizeof(UserLocation::_impl_.timestamp_)
      - PROTOBUF_FIELD_OFFSET(UserLocation, _impl_.latitude_)>(
          reinterpret_cast<char*>(&_impl_.latitude_),
          reinterpret_cast<char*>(&other->_impl_.latitude_));
}

std::string UserLocation::GetTypeName() const {
  return "turms.client.model.proto.UserLocation";
}

// @@protoc_insertion_point(namespace_scope)
}  // namespace proto
}  // namespace model
}  // namespace client
}  // namespace turms
namespace google {
namespace protobuf {
}  // namespace protobuf
}  // namespace google
// @@protoc_insertion_point(global_scope)
#include "google/protobuf/port_undef.inc"
