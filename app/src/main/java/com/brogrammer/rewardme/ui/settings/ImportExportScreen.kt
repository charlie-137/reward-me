package com.brogrammer.rewardme.ui.settings


import android.content.ContentResolver
import android.net.Uri
import android.provider.OpenableColumns
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.brogrammer.rewardme.data.model.ConversionRate
import com.brogrammer.rewardme.ui.components.NumberBox
import com.brogrammer.rewardme.ui.components.TextBox
import com.brogrammer.rewardme.utils.exportDatabaseToJson
import com.brogrammer.rewardme.utils.importDatabaseFromJson
import com.brogrammer.rewardme.viewModel.SettingsViewModel
import kotlinx.coroutines.launch

@Composable
fun ImportExportScreen(
    navController: NavController,
) {

    var selectedFileName by remember { mutableStateOf("No file selected") }
    var selectedFileUri by remember { mutableStateOf<Uri?>(null) }
    var importing by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    // File Picker Launcher

    val filePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        if (uri != null) {
            selectedFileUri = uri
            selectedFileName = getFileName(uri, context.contentResolver)
        }
    }


    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        Text(
            "Export/Import Database",
            style = MaterialTheme.typography.bodyMedium
        )


        Button(
            onClick = {
                coroutineScope.launch {
                    exportDatabaseToJson(context)
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Export Database", fontWeight = FontWeight.Bold)
        }



        Button(
            onClick = {
                filePickerLauncher.launch ("application/json")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Select File", fontWeight = FontWeight.Bold)
        }

        Text(
            "Selected File: $selectedFileName",
            style = MaterialTheme.typography.bodySmall
        )

        Button(
            onClick = {
                selectedFileUri?.let { uri ->
                    importing = true
                    coroutineScope.launch {
                        importDatabaseFromJson(context, uri)
                        importing = false
                        Toast.makeText(context, "Database imported successfully!", Toast.LENGTH_SHORT).show()
                    }
                }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = !importing
        ) {
            Text("Import Database")
        }

        // Optionally, you can add another button for importing data if necessary.
    }

}

fun getFileName(uri: Uri, contentResolver: ContentResolver): String {
    var fileName = "Unknown"
    contentResolver.query(uri, null, null, null, null)?.use { cursor ->
        val nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
        if (cursor.moveToFirst() && nameIndex >= 0) {
            fileName = cursor.getString(nameIndex)
        }
    }
    return fileName
}
