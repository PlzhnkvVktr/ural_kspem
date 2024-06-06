package ru.avem.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.avem.modules.tests.ikas.IKASScreen
import ru.avem.modules.tests.mgr.MGRScreen
import ru.avem.viewmodels.MainScreenViewModel

@Composable
fun TestListContainer(viewModel: MainScreenViewModel) {

    fun checkTest (item: MainScreenViewModel.TestEnum): Boolean {
        return when (item) {
            MainScreenViewModel.TestEnum.nameMGR -> viewModel.testList.add(MGRScreen(viewModel))
            MainScreenViewModel.TestEnum.nameVIU -> viewModel.testList.add(IKASScreen(viewModel))
            MainScreenViewModel.TestEnum.nameIKAS -> viewModel.testList.add(MGRScreen(viewModel))
            MainScreenViewModel.TestEnum.nameHH -> viewModel.testList.add(IKASScreen(viewModel))
            MainScreenViewModel.TestEnum.nameMV -> viewModel.testList.add(MGRScreen(viewModel))
        }
    }

    Column(
        modifier = Modifier.fillMaxWidth(0.8f).border(2.dp, Color.DarkGray)
    ) {
        viewModel.testMap.forEach { item ->
            Row(
                modifier = Modifier.fillMaxWidth().height(70.dp).clickable {
                    item.value.value = !item.value.value

                    val found = viewModel.testList.find { it.testName == item.key.testName }
                    if (found != null) {
                        viewModel.testList.remove(found)
                    } else {
                        checkTest(item.key)
                    }

                    viewModel.testsLine.value = viewModel.testList.iterator()
                    viewModel.startTestButton.value = viewModel.testsLine.value.hasNext()
                },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = item.value.value,
                    onCheckedChange = {
                        item.value.value = it

                        val found = viewModel.testList.find { it.testName == item.key.testName }
                        if (found != null) {
                            viewModel.testList.remove(found)
                        } else {
                            checkTest(item.key)
                        }

                        viewModel.testsLine.value = viewModel.testList.iterator()
                        viewModel.startTestButton.value = viewModel.testsLine.value.hasNext()
                    }
                )
                    Text(item.key.testName, fontSize = 26.sp)
            }
        }
    }
}
