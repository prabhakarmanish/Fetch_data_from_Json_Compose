package prabhakar.manish.json

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import prabhakar.manish.json.Data.SampleData


@Composable
fun SampleDataScreen() {
    val context = LocalContext.current
    val jsonFileString = getJsonDataFromAssets(context.assets, "sample_data.json")

    val gson = Gson()
    val listSampleDataType = object : TypeToken<List<SampleData>>() {}.type
    val sampleDataList: List<SampleData> = gson.fromJson(jsonFileString, listSampleDataType)

    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(sampleDataList) { sampleData ->
            StudentCard(sampleData)
        }
    }
}

@Composable
fun StudentCard(sampleData: SampleData) {
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                Toast
                    .makeText(context, sampleData.name, Toast.LENGTH_SHORT)
                    .show()
            },
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(4.dp),
        shape = MaterialTheme.shapes.medium,
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(text = "Name: ${sampleData.name}", style = MaterialTheme.typography.titleMedium)
            Text(text = "Age: ${sampleData.age}", style = MaterialTheme.typography.bodyMedium)
            Text(
                text = "Occupation: ${sampleData.occupation}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Address: ${sampleData.address.street}, ${sampleData.address.city}, ${sampleData.address.zip}",
                style = MaterialTheme.typography.bodySmall
            )

            // Skills section
            Text(text = "Skills:", style = MaterialTheme.typography.titleSmall)
            sampleData.skills.forEach { skill ->
                Text(
                    text = skill,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }

            // Active status
            Text(
                text = "Is Active: ${if (sampleData.isActive) "Yes" else "No"}",
                style = MaterialTheme.typography.bodyMedium
            )

            // Projects section
            Text(text = "Projects:", style = MaterialTheme.typography.titleSmall)
            sampleData.projects.forEach { project ->
                Text(
                    text = "Name: ${project.name}",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(start = 8.dp)
                )
                Text(
                    text = "Description: ${project.description}",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(start = 8.dp)
                )
                Text(
                    text = "Completed: ${if (project.completed) "Yes" else "No"}",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    }
}