import { saveAs } from 'file-saver';
import Excel from 'exceljs';

export default function exportExcel(fileName: string, worksheetName: string, headers: Partial<Excel.Column>[], rows: any[]): Promise<void> {
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
            saveAs(blob, `${fileName}.xlsx`);
        });
}
