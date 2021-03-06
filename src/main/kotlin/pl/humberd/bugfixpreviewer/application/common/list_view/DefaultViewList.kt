package pl.humberd.bugfixpreviewer.application.common.list_view

class DefaultViewList<DataType>(
    data: List<DataType>,
    extra: ListViewExtra
) : ListView<DataType, ListViewExtra>(data, extra)
