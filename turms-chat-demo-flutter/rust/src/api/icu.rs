use icu_collator::{Collator, CollatorOptions, Strength};
use icu_locid::Locale;
use std::cell::RefCell;

struct CollatorInfo {
    locale: String,
    collator: Collator,
}

thread_local! {
    static CACHED_COLLATOR_INFO: RefCell<Option<CollatorInfo>> = RefCell::new(None);
}

fn init_collator_info(locale: &str) -> Option<CollatorInfo> {
    match locale
        .parse::<Locale>() {
        Ok(target_locale) => {
            let mut options = CollatorOptions::new();
            options.strength = Some(Strength::Tertiary);
            match Collator::try_new(&target_locale.into(), options) {
                Ok(collator) => {
                    CACHED_COLLATOR_INFO.replace(Some(CollatorInfo {
                        locale: locale.to_string(),
                        collator,
                    }));
                    CACHED_COLLATOR_INFO.take()
                }
                Err(_) => {
                    None
                }
            }
        }
        Err(_) => {
            None
        }
    }
}

fn get_locale_collator(locale: &str) -> Option<CollatorInfo> {
    let option = CACHED_COLLATOR_INFO.take();
    match option {
        Some(ref collator_info) => {
            if collator_info.locale == locale {
                return option;
            }
            init_collator_info(locale)
        }
        None => {
            init_collator_info(locale)
        }
    }
}

#[flutter_rust_bridge::frb(sync)]
pub fn compare_strings(locale: &str, s1: &str, s2: &str) -> Option<i8> {
    Some(get_locale_collator(locale)?.collator.compare(s1, s2) as i8)
}

#[flutter_rust_bridge::frb(sync)]
/// TODO: Use `strings: &[&str]` when supported.
pub fn compare_string_vec(locale: &str, strings: Vec<String>) -> Option<Vec<u16>> {
    match get_locale_collator(&locale) {
        None => {
            None
        }
        Some(collator) => {
            let mut indexes: Vec<usize> = (0..strings.len()).collect();
            indexes.sort_by(|a, b| {
                collator.collator.compare(&strings[*a], &strings[*b])
            });
            Some(indexes.iter().map(|i| *i as u16).collect())
        }
    }
}

#[cfg(test)]
mod tests {
    use crate::api::icu::compare_string_vec;

    #[test]
    fn test_compare_string_vec() {
        let vec1 = vec!["Murmurs of Earth", "James Chen", "窦唯", "Nina Simone"]
            .iter().map(|s| s.to_string()).collect();
        let vec2 = compare_string_vec("zh", vec1);
        println!("{:?}", vec2);
    }
}