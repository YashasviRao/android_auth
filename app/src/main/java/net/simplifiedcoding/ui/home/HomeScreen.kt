package net.simplifiedcoding.ui.home

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import net.simplifiedcoding.InventoryApp
import net.simplifiedcoding.InventoryTopAppBar
import net.simplifiedcoding.R
import net.simplifiedcoding.data.Item
import net.simplifiedcoding.list.StatefulCounter
import net.simplifiedcoding.list.WellnessTasksList
import net.simplifiedcoding.list.WellnessViewModel
import net.simplifiedcoding.navigation.ROUTE_HOME
import net.simplifiedcoding.navigation.ROUTE_LOGIN
import net.simplifiedcoding.ui.auth.AuthViewModel
import net.simplifiedcoding.ui.item.ItemDetailsDestination
import net.simplifiedcoding.ui.item.ItemEntryDestination
import net.simplifiedcoding.ui.navigation.NavigationDestination
import net.simplifiedcoding.ui.theme.AppTheme
import net.simplifiedcoding.ui.theme.spacing
import net.simplifiedcoding.ui.AppViewModelProvider
import java.text.NumberFormat

object HomeDestination : NavigationDestination {
    override val route = "home"
    override val titleRes = R.string.app_name
}
@Composable
fun HomeScreen(
    viewModel: AuthViewModel,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {

    viewModel?.currentUser?.let {
        UserInfo(navController,modifier,viewModel)
    }
}


@SuppressLint("SuspiciousIndentation")
@Composable
fun UserInfo(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: AuthViewModel,
    wellnessViewModel: WellnessViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
   val spacing = MaterialTheme.spacing
    val homeUiState by wellnessViewModel.homeUiState.collectAsState()
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(spacing.small)
        ) {
            StatefulCounter()

            WellnessTasksList(
                list = wellnessViewModel.tasks,
                onCheckedTask = { task, checked ->
                    wellnessViewModel.changeTaskChecked(task, checked)
                },
                onCloseTask = { task ->
                    wellnessViewModel.remove(task)
                }
            )

            Button(
                onClick = {
                    viewModel?.logout()
                    navController.navigate(ROUTE_LOGIN) {
                        popUpTo(ROUTE_HOME) { inclusive = true }
                    }
                },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = spacing.extraLarge)
            ) {
                Text(text = stringResource(id = R.string.logout))
            }
        }
}

//@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
//@Composable
//fun HomeScreenPreviewLight() {
//    AppTheme {
//        UserInfo( rememberNavController())
//    }
//}
//
//@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
//@Composable
//fun HomeScreenPreviewDark() {
//    AppTheme {
//        UserInfo( rememberNavController())
//    }
//}
