import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:intl/intl.dart';
import 'package:material_symbols_icons/symbols.dart';

import '../../../../../domain/file/models/file_info.dart';
import '../../../../../domain/file/services/file_service.dart';
import '../../../../../infra/data/t_async_data.dart';
import '../../../../../infra/units/file_size_extensions.dart';
import '../../../../l10n/app_localizations.dart';
import '../../../../l10n/view_models/app_localizations_view_model.dart';
import '../../../../l10n/view_models/date_format_view_models.dart';
import '../../../../themes/app_theme_extension.dart';
import '../../../../themes/sizes.dart';
import '../../../components/index.dart';
import 'file_icon/file_icon.dart';

class FilesPage extends ConsumerStatefulWidget {
  const FilesPage({super.key});

  @override
  ConsumerState<ConsumerStatefulWidget> createState() => _FilesPageState();
}

class _FilesPageState extends ConsumerState {
  TAsyncData<List<FileInfo>> _fileInfosData = const TAsyncData();

  @override
  void initState() {
    super.initState();
    TAsyncData.fromFuture(() => ref.read(fileServiceProvider)!.queryFiles())
        .forEach(
      (data) {
        if (mounted) {
          _fileInfosData = data;
          setState(() {});
        }
      },
    );
  }

  @override
  Widget build(BuildContext context) {
    final appThemeExtension = context.appThemeExtension;
    final appLocalizations = ref.watch(appLocalizationsViewModel);
    return _buildView(appThemeExtension, appLocalizations);
  }

  void _downloadOrOpen() {
    // TODO: download + download animation + use real file path
    // OpenFile.open('');
  }
}

extension _FilesPageView on _FilesPageState {
  Widget _buildView(
      AppThemeExtension appThemeExtension, AppLocalizations appLocalizations) {
    // use "add_Hm()" instead of "add_jm()"
    // to make the string short and concise
    final dateFormat = ref.watch(dateFormatViewModel_yMdHm);

    return Column(
      children: [
        _buildQueryFilters(appThemeExtension, appLocalizations),
        _buildTable(
            appThemeExtension, appLocalizations, dateFormat, _fileInfosData),
      ],
    );
  }

  Widget _buildQueryFilters(
      AppThemeExtension appThemeExtension, AppLocalizations appLocalizations) {
    final now = DateTime.now();
    return ColoredBox(
      color: appThemeExtension.homePageBackgroundColor,
      child: ConstrainedBox(
        constraints:
            const BoxConstraints.tightFor(height: Sizes.homePageHeaderHeight),
        child: Stack(children: [
          const TWindowControlZone(toggleMaximizeOnDoubleTap: true),
          Center(
            child: Padding(
              padding: EdgeInsets.symmetric(
                  // Use the same padding as the navigation rail
                  // so they are aligned.
                  horizontal: Sizes.subNavigationRailPadding.left,
                  vertical: 16),
              child: Row(
                spacing: Sizes.subNavigationRailPadding.left,
                children: [
                  SizedBox(
                    width: 200,
                    child: TSearchBar(hintText: appLocalizations.fileName),
                  ),
                  TDateRangePicker(
                    firstDate: DateTime(now.year - 3),
                    lastDate: now,
                    initialDateRange: DateTimeRange(
                      start: now.subtract(const Duration(days: 7)),
                      end: now,
                    ),
                  ),
                ],
              ),
            ),
          ),
        ]),
      ),
    );
  }

  Widget _buildTable(
      AppThemeExtension appThemeExtension,
      AppLocalizations appLocalizations,
      DateFormat dateFormat,
      TAsyncData<List<FileInfo>> fileInfosData) {
    if (!fileInfosData.isInitialized || fileInfosData.isLoading) {
      // TODO: polish UI
      return const Expanded(child: Center(child: CircularProgressIndicator()));
    }
    final fileInfos = fileInfosData.value!;
    return Expanded(
      child: TTable(
        header: TTableRow(cells: [
          const TTableDataCell(widget: Icon(Symbols.insert_drive_file_rounded)),
          _buildHeaderCell(appThemeExtension, appLocalizations.fileName),
          _buildHeaderCell(appThemeExtension, appLocalizations.fileUploadDate),
          _buildHeaderCell(appThemeExtension, appLocalizations.fileUploader),
          _buildHeaderCell(appThemeExtension, appLocalizations.fileSize),
          _buildHeaderCell(appThemeExtension, appLocalizations.progress),
        ]),
        rows: fileInfos
            .map((e) => TTableRow(
                  onTap: _downloadOrOpen,
                  cells: [
                    TTableDataCell(widget: FileIcon(fileFormat: e.type)),
                    _buildTextDataCell(appThemeExtension, e.name, false),
                    _buildTextDataCell(appThemeExtension,
                        dateFormat.format(e.uploadDate), true),
                    _buildTextDataCell(appThemeExtension, e.uploader, true),
                    _buildTextDataCell(appThemeExtension,
                        e.size.toHumanReadableFileSize(), true),
                    const TTableDataCell(
                        widget: Padding(
                      padding: EdgeInsets.only(right: 16),
                      child: RepaintBoundary(child: LinearProgressIndicator()),
                    )),
                  ],
                ))
            .toList(),
        columnOptions: [
          const TTableColumnOption(width: 0.05),
          const TTableColumnOption(width: 0.30),
          const TTableColumnOption(width: 0.15),
          const TTableColumnOption(width: 0.225),
          const TTableColumnOption(width: 0.10),
          const TTableColumnOption(width: 0.175),
        ],
      ),
    );
  }

  TTableDataCell _buildHeaderCell(
          AppThemeExtension appThemeExtension, String title) =>
      TTableDataCell(
          widget: Text(title,
              style: appThemeExtension.fileTableTitleTextStyle,
              overflow: TextOverflow.ellipsis));

  TTableDataCell _buildTextDataCell(
          AppThemeExtension appThemeExtension, String text, bool isSecondary) =>
      TTableDataCell(
          widget: Text(
        text,
        overflow: TextOverflow.ellipsis,
        maxLines: 2,
        style: isSecondary ? appThemeExtension.fileTableCellTextStyle : null,
      ));
}
