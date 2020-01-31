const fileSaver = require('file-saver');
const Excel = require('exceljs/lib/exceljs.bare.js');

export default function exportExcel(fileName, worksheetName, headers, rows) {
    const workbook = new Excel.Workbook();
    workbook.creator = 'Turms';
    workbook.created = new Date();
    const worksheet = workbook.addWorksheet(worksheetName);
    worksheet.columns = headers;
    worksheet.addRows(rows);
    return workbook.xlsx.writeBuffer()
        .then(buffer => {
            const blob = new Blob([buffer], {
                type: 'application/vnd.ms-excel;charset=utf-8'
            });
            fileSaver.saveAs(blob, `${fileName}.xlsx`);
            return buffer;
        });
}
