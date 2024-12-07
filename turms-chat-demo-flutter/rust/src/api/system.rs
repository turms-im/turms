use sysinfo::{Disks, Pid, ProcessRefreshKind, ProcessesToUpdate, System};

pub struct DiskSpaceInfo {
    pub path: String,
    pub total: u64,
    pub available: u64,
}

#[flutter_rust_bridge::frb(sync)]
pub fn get_disk_space_infos() -> Vec<DiskSpaceInfo> {
    Disks::new_with_refreshed_list().list().iter().map(|disk| {
        return DiskSpaceInfo {
            path: disk.mount_point().to_str().unwrap().to_string(),
            total: disk.total_space(),
            available: disk.available_space(),
        };
    })
        .collect::<Vec<DiskSpaceInfo>>()
}

#[flutter_rust_bridge::frb(sync)]
pub fn is_process_running(pid: u32) -> bool {
    let mut s = System::new();
    s.refresh_processes_specifics(ProcessesToUpdate::All, true, ProcessRefreshKind::default());
    let pid_to_process = s.processes();
    pid_to_process.contains_key(&Pid::from_u32(pid))
}

#[cfg(test)]
mod tests {
    use crate::api::system::is_process_running;

    #[test]
    fn test_is_process_running() {
        let is_running = is_process_running(std::process::id());
        assert_eq!(is_running, true);
    }
}