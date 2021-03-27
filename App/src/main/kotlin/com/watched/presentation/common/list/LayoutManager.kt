package com.watched.presentation.common.list

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager

class HorizontalLinearLayoutManager(
    context: Context
) : LinearLayoutManager(context, HORIZONTAL, false)

class VerticalLinearLayoutManager(
    context: Context
) : LinearLayoutManager(context, VERTICAL, false)