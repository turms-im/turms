import Cocoa
import FlutterMacOS

private class AppHostApiImpl: AppHostApi {
  func getDiskSpace(path: String) throws -> DiskSpaceInfo {
    let fileURL = URL(fileURLWithPath: path)
       do {
           let values = try fileURL.resourceValues(forKeys: [.volumeTotalCapacityKey])
           let total = values.volumeTotalCapacity
           let free = values.volumeAvailableCapacityForImportantUsage
           return DiskSpaceInfo(total, free, free)
       } catch {
           return DiskSpaceInfo(-1, -1, -1)
       }
  }
}

class MainFlutterWindow: NSWindow {
  override func awakeFromNib() {
    let flutterViewController = FlutterViewController()
    let windowFrame = self.frame
    self.contentViewController = flutterViewController
    self.setFrame(windowFrame, display: true)

    RegisterGeneratedPlugins(registry: flutterViewController)

    let hostApi = AppHostApiImpl()
    AppHostApiSetup.setUp(
      binaryMessenger: flutterViewController.engine.binaryMessenger, api: hostApi)

    super.awakeFromNib()
  }
}